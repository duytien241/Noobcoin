/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Noobchain.Wallet;

/**
 *
 * @author lucth
 */
public class sodu extends javax.swing.JPanel {
	public static Wallet user;
    /**
     * Creates new form chitietluong
     */
    public sodu(Wallet tmp) {
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
        xacnhan = new javax.swing.JButton();
        huy = new javax.swing.JButton();
        sodutaikhoan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sodutaikhoan1 = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(440, 340));
        setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tài khoản:");
        add(jLabel3);
        jLabel3.setBounds(100, 130, 130, 17);

        sodutaikhoan.setText(""+user.getBalance());
        add(sodutaikhoan);
        sodutaikhoan.setBounds(220, 170, 100, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Kiểm tra số dư");
        add(jLabel1);
        jLabel1.setBounds(180, 70, 130, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số dư tài khoản:");
        add(jLabel4);
        jLabel4.setBounds(100, 170, 130, 17);

        sodutaikhoan1.setText(""+user.username);
        add(sodutaikhoan1);
        sodutaikhoan1.setBounds(220, 130, 100, 20);
    }// </editor-fold>                        

    private void xacnhanActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void huyActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    }                                   


    // Variables declaration - do not modify                     
    private javax.swing.JButton huy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField sodutaikhoan;
    private javax.swing.JTextField sodutaikhoan1;
    private javax.swing.JButton xacnhan;
    // End of variables declaration                   
}
