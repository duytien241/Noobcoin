/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Noobchain.NoobCoin;
import Noobchain.Wallet;

/**
 *
 * @author lucth
 */
public class datso extends javax.swing.JPanel {
	public static String time;
	public static String reward;
	public static Wallet user;
	
    /**
     * Creates new form chitietluong
     */
    public datso(String time, Wallet tmp) {
    	this.time = time;
    	this.user = tmp;
    	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	jLabel3 = new javax.swing.JLabel();
  
    	jLabel3 = new javax.swing.JLabel();
        so = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        thoigian = new javax.swing.JTextField();
        xacnhan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tongiaithuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lastluckycount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lastlucky = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(440, 340));
        setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số người chiến thắng kì trước:");
        add(jLabel3);
        jLabel3.setBounds(50, 100, 200, 17);

        so.setText("");
        add(so);
        so.setBounds(250, 220, 100, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Con số may mắn");
        add(jLabel1);
        jLabel1.setBounds(180, 20, 150, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Đặt số của bạn:");
        add(jLabel4);
        jLabel4.setBounds(140, 220, 110, 17);

        thoigian.setText(NoobCoin.smc.time);
        add(thoigian);
        thoigian.setBounds(250, 180, 100, 20);

        xacnhan.setText("Xác nhận");
        if(user != null) {
        	add(xacnhan);
        }
        xacnhan.setBounds(180, 270, 110, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Thời gian kết thúc:");
        add(jLabel5);
        jLabel5.setBounds(120, 180, 120, 17);

        tongiaithuong.setText(""+NoobCoin.walletAdmin.getBalance());
        add(tongiaithuong);
        tongiaithuong.setBounds(250, 140, 100, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Số may mắn:");
        add(jLabel6);
        jLabel6.setBounds(140, 60, 90, 17);

        lastluckycount.setText(""+NoobCoin.smc.count);
        add(lastluckycount);
        lastluckycount.setBounds(250, 100, 59, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tổng giải thưởng:");
        add(jLabel7);
        jLabel7.setBounds(130, 140, 120, 17);

        lastlucky.setText(""+NoobCoin.smc.lucky);
        add(lastlucky);
        lastlucky.setBounds(250, 60, 59, 20);
        xacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	System.out.print(so.getText());
            	NoobCoin.smc.addUser(user, Integer.parseInt(so.getText()));
            	NoobCoin.smc.listWalletJoin.forEach((keyInt, valueInt) -> System.out.println(
            	        "Key = " + keyInt + ", value = " + valueInt));
            }
        });
    }// </editor-fold>                        
                                


    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField lastlucky;
    private javax.swing.JTextField lastluckycount;
    private javax.swing.JTextField so;
    private javax.swing.JTextField thoigian;
    private javax.swing.JButton xacnhan;
    private javax.swing.JTextField tongiaithuong;
    // End of variables declaration                   
}