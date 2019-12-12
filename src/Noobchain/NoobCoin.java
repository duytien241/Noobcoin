package Noobchain;
import java.security.PublicKey;
import java.security.Security;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;
import static org.bouncycastle.crypto.tls.CipherType.block;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import Application.GiaoDienkhach;
import Application.showBlock;
import Noobchain.Block;

public class NoobCoin {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();
	public static int difficulty = 3;
	public static float minimumTransaction = 0.1f;
	public static Wallet walletA;
	public static Wallet walletB;
	public static Wallet walletAdmin;
	public static Block currentBlock;
	public static Transaction genesisTransaction;	
	public static ArrayList<Wallet> listWallet = new ArrayList<>();
	public static SmartContract smc = new SmartContract();

	public static void addWallet() {
		walletA = new Wallet("nguoidung1", "admin");
		walletB = new Wallet("nguoidung2", "admin");
		walletAdmin = new Wallet("admin","admin");
		listWallet.add(walletA);
		listWallet.add(walletB);
		listWallet.add(walletAdmin);
		Wallet coinbase = new Wallet();
		genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 1000f, null);
		genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
		genesisTransaction.transactionId = "0"; //manually set the transaction id
		genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
		UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.
		System.out.println("Creating and Mining Genesis block... ");
		Block genesis = new Block("0");
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);
		
		Block block1 = new Block(genesis.hash);
		block1.addTransaction(walletA.sendFunds(walletB.publicKey, 500f));
		addBlock(block1);
		createBlock.start();
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
		tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("#Current Hashes not equal");
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("#Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("#This block hasn't been mined");
				return false;
			}
			
			//loop thru blockchains transactions:
			TransactionOutput tempOutput;
			for(int t=0; t <currentBlock.transactions.size(); t++) {
				Transaction currentTransaction = currentBlock.transactions.get(t);
				
				if(!currentTransaction.verifySignature()) {
					System.out.println("#Signature on Transaction(" + t + ") is Invalid");
					return false; 
				}
				if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
					System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
					return false; 
				}
				
				for(TransactionInput input: currentTransaction.inputs) {	
					tempOutput = tempUTXOs.get(input.transactionOutputId);
					
					if(tempOutput == null) {
						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
						return false;
					}
					
					if(input.UTXO.value != tempOutput.value) {
						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
						return false;
					}
					
					tempUTXOs.remove(input.transactionOutputId);
				}
				
				for(TransactionOutput output: currentTransaction.outputs) {
					tempUTXOs.put(output.id, output);
				}
				
				if( currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
					return false;
				}
				if( currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
					System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
					return false;
				}
				
			}
			
		}
		System.out.println("Blockchain is valid");
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
		GiaoDienkhach.shB.reload(StringUtil.getJson(newBlock));
		isChainValid();
	}
	final static Thread createBlock = new Thread() {
        @Override
        public void run() {
        	while(true) {
        		
        		try {
					sleep(5000);
					Noobchain.Block tmp = Noobchain.NoobCoin.blockchain.get( Noobchain.NoobCoin.blockchain.size()-1);
	            	Noobchain.Block tmp2 = new Noobchain.Block(tmp.hash);
	                Noobchain.NoobCoin.addBlock(tmp2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    };
}
