package Application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.Border;

import Noobchain.NoobCoin;
import Noobchain.Wallet;


public class dangky extends JFrame implements ActionListener,ItemListener{
	/**
	 * 
	 */
	public dangky() {
		super("Đăng nhập");
		taikhoan = new JLabel("Tài khoản:");
		matkhau = new JLabel("Mật khẩu:");
		nhaptk = new JTextArea(15,10);
		nhapmk  = new JPasswordField();
		blogin = new JButton("Đăng ký");
		jpdn = new JPanel();
		jpdn.setLayout(null);
		
		taikhoan.setBounds(20,10,60,30);
		matkhau.setBounds(20,60,60,30);
		nhaptk.setBounds(100, 10, 150, 30);
		nhaptk.setBorder(panelBorder);
		nhaptk.setFont(nhaptk.getFont().deriveFont(14f));
		nhapmk.setBounds(100,60,150,30);
		nhapmk.setFont(nhaptk.getFont().deriveFont(14f));
		blogin.setBounds(105,120,100,30);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(checkdg);
		genderGroup.add(checknv);
		jpdn.add(blogin);
		jpdn.add(taikhoan);
		jpdn.add(matkhau);
		jpdn.add(nhaptk);
		jpdn.add(nhapmk);
		blogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String password = new String(nhapmk.getPassword());
				Wallet tmp = new Wallet(nhaptk.getText(), password);
				NoobCoin.listWallet.add(tmp);
			}
		});
		this.add(jpdn);
	}
	public static void dangkychay() {
		dn = new dangky();
		dn.setSize(300,250);
		dn.setVisible(true);
		dn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static dangky dn;
	private JPanel jpdn;
	private JLabel taikhoan, matkhau;
	public JTextArea nhaptk;
	public JPasswordField nhapmk;
	private Border panelBorder = BorderFactory
		      .createLineBorder(Color.BLACK);
	public JButton blogin;
	public static JRadioButton checknv,checkdg;

	public void itemStateChanged(ItemEvent e) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
