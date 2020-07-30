import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class MainAppliction extends JFrame {
	
	public final static String c_SrcFolder = "./img/";
	private JPanel JFrameMain;
	private JButton btnShowPlayers;
	private JButton btnGameInfo;
	private JButton btnTraningSet;
	private JButton btnExit;
	private Games game;
	private TeamSquad teamSqouad;
	private TraningSet traningSet;





	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppliction frame = new MainAppliction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainAppliction() {
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\benshabi\\Desktop\\ProjectJava\\CoachSystem\\src\\img\\bkg.jpg"));

		initComponents();
		Handler handler = new Handler();
		btnShowPlayers.addActionListener(handler);
		btnGameInfo.addActionListener(handler);
		btnTraningSet.addActionListener(handler);
		btnExit.addActionListener(handler);
		

	}

	
	private void initComponents() {
		ImageIcon iconBackGround = new ImageIcon(c_SrcFolder + "bkg.jpg");
		setResizable(false);
		setBackground(new Color(255, 248, 220));
		setTitle("Main Apliction");
		setBounds(100, 100, 551, 391);
		JFrameMain = new JPanel(); 
		//contentPane.setBackground(new Color(255, 248, 220));
		JFrameMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JFrameMain);
		JFrameMain.setLayout(null);
		JLabel lblBackGround = new JLabel("",iconBackGround,JLabel.CENTER);
		lblBackGround.setBounds(0, 0, 551, 391);
		JLabel lblNewLabel = new JLabel("Desk-Coach");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Small", Font.BOLD, 16));
		lblNewLabel.setBounds(165, 11, 214, 33);
		JFrameMain.add(lblNewLabel);
		//JFrameMain.add(lblBackGround);
		
		btnShowPlayers = new JButton("Team Squad");
		btnShowPlayers.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 16));
		btnShowPlayers.setBounds(165, 70, 229, 43);
		JFrameMain.add(btnShowPlayers);
		
		btnGameInfo = new JButton("Game information");
		btnGameInfo.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 16));
		btnGameInfo.setBounds(165, 124, 229, 43);
		JFrameMain.add(btnGameInfo);
		
		btnTraningSet = new JButton("Training");
		btnTraningSet.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 16));
		btnTraningSet.setBounds(165, 178, 229, 43);
		JFrameMain.add(btnTraningSet);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 16));
		btnExit.setBounds(238, 276, 89, 43);
		JFrameMain.add(btnExit);
		
	}


	class Handler implements ActionListener
	{

		@Override
	    public void actionPerformed(ActionEvent evt) 
	    {
			String btnClick = evt.getActionCommand();
			try 
			{
				if(btnClick == "Team Squad")
				{
					if(teamSqouad !=null && teamSqouad.isVisible())
						return;
					teamSqouad = new TeamSquad();
					teamSqouad.setVisible(true);
					//setVisible(false);
				}
				if(btnClick == "Game information")
				{
					if(game !=null && game.isVisible())
						return;
					game = new Games();
					game.setVisible(true);
					//setVisible(false);
					//dispose(); //Destroy the JFrame object
	
				}
				if(btnClick == "Training")
				{
					if(traningSet !=null && traningSet.isVisible())
						return;
					traningSet = new TraningSet();
					traningSet.setVisible(true);
					//setVisible(false);
					//dispose(); //Destroy the JFrame object
				}
	
				if(btnClick == "Exit" )
					System.exit(0);
			}catch(Exception e1) {System.out.print(e1);}
	    }
		
	}
}
