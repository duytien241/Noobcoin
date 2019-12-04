package Noobchain;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class SmartContract {
	private int res;
	public static Map< Wallet,List<Integer>> listWalletJoin = new HashMap< Wallet,List<Integer>>();
	private Wallet userA, userB;
	private int predictA, predictB;
	public static int lucky;
	public static int count = 0;
	private final float betMoney = 100.0f;
	private Transaction transaction;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static LocalDateTime now = LocalDateTime.now().plusMinutes(1);  
	public static String time = dtf.format(now);
	public SmartContract() {
		execute();
	}
	
        /*
        Start smartcontract
        */
        public void start() {
        	checkResult();
        }
        
    public void addUser(Wallet w, int so) {
       transaction = w.sendFunds(NoobCoin.walletAdmin.publicKey, betMoney);
       if(transaction == null ) {
    	   return;
       }
       transaction = w.sendFunds(NoobCoin.walletAdmin.publicKey, betMoney);
	   Noobchain.Block tmp = Noobchain.NoobCoin.blockchain.get( Noobchain.NoobCoin.blockchain.size()-1);
   	   Noobchain.Block tmp2 = new Noobchain.Block(tmp.hash);
       tmp2.addTransaction(transaction);
       Noobchain.NoobCoin.addBlock(tmp2);
       if (listWalletJoin.containsKey(w)) {
    	   listWalletJoin.get(w).add(so);
       }
       else {
    	   listWalletJoin.put(w, new ArrayList<Integer>());
    	   listWalletJoin.get(w).add(so);
       }
        }
	public void execute() {
		checkResult();
	}
	
	private void checkResult() {
		final Thread checkResult;
            checkResult = new Thread() {
                @Override
                public void run() {
                	int tmp3 = 0;
                	while(true) {
                    try {
                        sleep(100000);
                        Random r = new Random();
                        lucky = r.nextInt((int)1e9)%5;
                        System.out.printf("\nCon số may mắn là : %d", lucky);
                        count = 0;
                        float reward = 0;
                        Set< Map.Entry< Wallet,List<Integer>> > st = listWalletJoin.entrySet();    
              		  
             	       for (Map.Entry< Wallet,List<Integer>> me:st) 
             	       { 
             	    	   for(int i: me.getValue()) {
             	    		   if(i==lucky) {
             	    			   count ++;
             	    		   }
             	    	   }
             	       }
             	      reward = NoobCoin.walletAdmin.getBalance()/count;
             	      for (Map.Entry< Wallet,List<Integer>> me:st) 
            	       { 
	             	    	for(int i: me.getValue()) {
	           	    		   if(i==lucky) {
	           	    			   transaction = NoobCoin.walletAdmin.sendFunds(me.getKey().publicKey, reward);
	           	    			   Noobchain.Block tmp = Noobchain.NoobCoin.blockchain.get( Noobchain.NoobCoin.blockchain.size()-1);
		           	    	   	   Noobchain.Block tmp2 = new Noobchain.Block(tmp.hash);
		           	    	       tmp2.addTransaction(transaction);
		           	    	       Noobchain.NoobCoin.addBlock(tmp2);
	           	    		   }
	           	    	   }
            	    	  
            	       }
             	     if(count > 0) {
             	    	 listWalletJoin.clear();
             	     }
             	     System.out.printf("\n Có %d người chiến thắng", count);
                        
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                	}
                }
            };
        checkResult.start();
	}
}
