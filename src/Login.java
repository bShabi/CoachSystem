import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DB;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JLabel lblNewLabel_1;
	private JPasswordField password;
	private DB DateBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public Login() {
		DateBase = new DB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(34, 114, 147, 27);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(34, 143, 290, 33);
		contentPane.add(username);
		username.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(34, 177, 117, 28);
		contentPane.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(34, 204, 282, 33);
		contentPane.add(password);
		
		JButton btnLogin = new JButton("Login");
		//Login Section ,active after click Button Login
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(username.getText().isEmpty() && password.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please currect deitals");
						return;
					}
					String dbName = "CoachSystem";
					String dbUserName = "root";
					String dbPassword = "Pa$$word";
					Class.forName("com.mysql.jdbc.Driver");
					Connection con =DriverManager.getConnection("jdbc:mysql://192.168.64.2:3306/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8");
					Statement stmt =con.createStatement();
					String entryUser = username.getText().toString();
					String entPass = password.getText().toString();
					
					//String sql = "SELECT * from `login` WHERE Username ='"+entryUser+"' AND Password '" + entPass +"'";
					//System.out.print(sql + "\n");
					String sql = "SELECT * FROM `login` WHERE Username='"+entryUser +"' AND Password='"+entPass+"'";
					//ResultSet rs=stmt.executeQuery(sql);
					//System.out.print(rs);
					if(DateBase.Login(sql))
					{
						JOptionPane.showMessageDialog(null,"Login Success!");
						MainAppliction PanelConnect = new MainAppliction();
						
						PanelConnect.setVisible(true);
						setVisible(false);
						dispose(); //Destroy the JFrame object
					}
					else 
						JOptionPane.showMessageDialog(null,"Bad login try agin!");
						
				}catch(Exception e1) {System.out.print(e1);}
			}
		});
		btnLogin.setBounds(143, 284, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblLoggin = new JLabel("Login");
		lblLoggin.setForeground(SystemColor.menuText);
		lblLoggin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblLoggin.setBackground(new Color(255, 255, 224));
		lblLoggin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoggin.setBounds(34, 25, 274, 50);
		contentPane.add(lblLoggin);
		
	
		
	}
}



