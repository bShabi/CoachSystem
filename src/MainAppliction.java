import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

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
		initComponents();
		Handler handler = new Handler();
		btnShowPlayers.addActionListener(handler);
		btnGameInfo.addActionListener(handler);
		btnTraningSet.addActionListener(handler);
		btnExit.addActionListener(handler);

	}

	private void initComponents() {
		setResizable(false);
		setBounds(100, 100, 551, 391);
		setTitle("Main Apliction");
		
		// Set background image to panel
		try {
			BufferedImage backgroundImage = ImageIO.read(new File(c_SrcFolder + "bkg.jpg"));
			Image scaledImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
			JFrameMain = new ImagePanel(scaledImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JFrameMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JFrameMain);
		JFrameMain.setLayout(null);
		JLabel lblNewLabel = new JLabel("Desk-Coach");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Small", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		lblNewLabel.setBounds(165, 11, 214, 33);
		JFrameMain.add(lblNewLabel);

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

	class ImagePanel extends JPanel {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);
	    }
	}
	
	class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {
			String btnClick = evt.getActionCommand();
			try {
				if (btnClick == "Team Squad") {
					if (teamSqouad != null && teamSqouad.isVisible())
						return;
					teamSqouad = new TeamSquad();
					teamSqouad.setVisible(true);
					// setVisible(false);
				}
				if (btnClick == "Game information") {
					if (game != null && game.isVisible())
						return;
					game = new Games();
					game.setVisible(true);
					// setVisible(false);
					// dispose(); //Destroy the JFrame object

				}
				if (btnClick == "Training") {
					if (traningSet != null && traningSet.isVisible())
						return;
					traningSet = new TraningSet();
					traningSet.setVisible(true);
					// setVisible(false);
					// dispose(); //Destroy the JFrame object
				}

				if (btnClick == "Exit")
					System.exit(0);
			} catch (Exception e1) {
				System.out.print(e1);
			}
		}

	}
}
