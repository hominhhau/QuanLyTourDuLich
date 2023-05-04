package giaoDien;

import javax.swing.*;
import javax.swing.border.Border;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;
import roundedButtonUI.RoundedButtonUI_v2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class LoginForm extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblUserName, lblPassword, lblMessage;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnCancel;
	private NhanVien_DAO nv_dao;

	public String ma, ten;
	public int sdt;

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}

	public LoginForm() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nv_dao = new NhanVien_DAO();

		setTitle("Đăng nhập");
		setSize(720, 405);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);

		ImageIcon background = new ImageIcon("demo\\login.png");
		JLabel backgroundLabel = new JLabel(background);

		backgroundLabel.setBounds(0, 0, 1280, 720);
		backgroundLabel.setOpaque(true);
		backgroundLabel.setPreferredSize(new Dimension(1280, 720));
		backgroundLabel.setBackground(Color.WHITE);
		backgroundLabel.setLayout(null);

		lblUserName = new JLabel("User name:");
		lblUserName.setFont(new Font("", Font.BOLD, 20));
		lblUserName.setForeground(Color.BLACK);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("", Font.BOLD, 20));
		lblPassword.setForeground(Color.BLACK);

		lblMessage = new JLabel();
		lblMessage.setFont(new Font("", Font.BOLD, 15));
		lblMessage.setForeground(Color.RED);

		Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("", Font.BOLD, 20));
		txtUserName.setBorder(roundedBorder);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("", Font.BOLD, 20));
		txtPassword.setBorder(roundedBorder);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setPreferredSize(new Dimension(100, 50));
		btnLogin.setForeground(Color.ORANGE);
		btnLogin.setOpaque(true);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnLogin.setUI(new RoundedButtonUI_v2());

		btnCancel = new JButton("Hủy");
		btnCancel.setPreferredSize(new Dimension(100, 50));
		btnCancel.setForeground(Color.ORANGE);
		btnCancel.setOpaque(true);
		btnCancel.setBorderPainted(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnCancel.setUI(new RoundedButtonUI_v2());

		lblUserName.setBounds(280, 200, 200, 20);
		txtUserName.setBounds(400, 200, 200, 20);
		lblPassword.setBounds(280, 250, 200, 20);
		txtPassword.setBounds(400, 250, 200, 20);
		btnLogin.setBounds(320, 330, 100, 30);
		btnCancel.setBounds(450, 330, 100, 30);
		lblMessage.setBounds(280, 290, 330, 20);
		lblMessage.setHorizontalAlignment(JLabel.CENTER);

		backgroundLabel.add(lblUserName);
		backgroundLabel.add(txtUserName);
		backgroundLabel.add(lblPassword);
		backgroundLabel.add(txtPassword);
		backgroundLabel.add(btnLogin);
		backgroundLabel.add(btnCancel);
		backgroundLabel.add(lblMessage);

		add(backgroundLabel, BorderLayout.CENTER);

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);

	}

	public static void main(String[] args) {
		new LoginForm().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnLogin) {
			List<NhanVien> list = nv_dao.getalltbNhanVien();
			int i = 0;
			for (NhanVien nv : list) {
				String userNameValue = txtUserName.getText();
				String passwordValue = String.valueOf(txtPassword.getPassword());
				if (userNameValue.equals(nv.getMaNV()) && passwordValue.equals(nv.getMatKhau())) {
					try {
						File file = new File("temp\\tmp.txt");
						FileOutputStream fos = new FileOutputStream(file);
						if (!file.exists()) {
							file.createNewFile();
						}
						byte[] bytesArray = String.valueOf(i).getBytes();
						fos.write(bytesArray);
						fos.flush();
						fos.close();
					} catch (IOException ee) {
						ee.printStackTrace();
					}
					 new GUI().setVisible(true);
					 dispose();
				} else {
					i++;
					lblMessage.setText("Tên đăng nhập hoặc mật khẩu không đúng");
				}
			}

		} else if (obj == btnCancel) {
			txtUserName.setText("");
			txtPassword.setText("");
			txtUserName.requestFocus();

		}
	}
}
