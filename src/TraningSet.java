import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TraningSet extends JFrame {

	
	/**
	 * 
	 */
	public final static String c_SrcFolder = "./img/";
	public static Graphics g;
	private ArrayList<JButton> listIcon;
	private JLabel lblBoard;
	private JPanel contentPane;
	private Point pointToDrawIcon;
    private String IconToDraw;
	
	
	
    public Point getPointToDrawIcon() {
		return pointToDrawIcon;
	}

	public void setPointToDrawIcon(Point pointToDrawIcon) {
		this.pointToDrawIcon = pointToDrawIcon;
	}

	public String getIconToDraw() {
		return IconToDraw;
	}

	public void setIconToDraw(String iconToDraw) {
		IconToDraw = iconToDraw;
	}

	public TraningSet() throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1280, 720);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 224));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
		
		//set icon to Button
		ImageIcon icon_O = new ImageIcon(c_SrcFolder + "IconO.png");
		ImageIcon icon_X = new ImageIcon(c_SrcFolder + "IconX.png");
		ImageIcon icon_Cone = new ImageIcon(c_SrcFolder + "IconCone.png"); 
		ImageIcon icon_Text = new ImageIcon(c_SrcFolder + "IconText.png"); 
		
		
		JPanel pnlBoard = new JPanel();
		pnlBoard.setBackground(new Color(85, 107, 47));
		pnlBoard.setBounds(10, 87, 1200, 600);
		contentPane.add(pnlBoard);
		pnlBoard.setLayout(null);


		
		lblBoard = new JLabel(new ImageIcon(getClass().getResource(c_SrcFolder + "Board.jpeg")));
		lblBoard.setBounds(183, 5, 900, 600);
		pnlBoard.add(lblBoard);
		
		JButton btnIconX = new JButton("IconX");
		btnIconX.setBounds(25, 115, 100, 99);
		pnlBoard.add(btnIconX);
		btnIconX.setIcon(icon_X);
		
		
		JButton btnIconO = new JButton("IconO");
		btnIconO.setBounds(25, 5, 100, 99);
		pnlBoard.add(btnIconO);
		btnIconO.setIcon(icon_O);
		
		JButton btnIconCone = new JButton("IconCone");
		btnIconCone.setBounds(25, 225, 100, 91);
		pnlBoard.add(btnIconCone);
		btnIconCone.setIcon(icon_Cone);
		
		
		
		
		JButton btnIconText = new JButton("IconText");
		btnIconText.setBounds(35, 339, 90, 91);
		pnlBoard.add(btnIconText);
		btnIconText.setIcon(icon_Text);
		
		JLabel lblWellcome = new JLabel("Draw Traning");
		lblWellcome.setForeground(new Color(0, 0, 128));
		lblWellcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		lblWellcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWellcome.setBounds(508, 28, 251, 33);
		contentPane.add(lblWellcome);
		
		
		
		ClickMouseListener MouseClick = new ClickMouseListener(this);
		btnIconX.addMouseListener(MouseClick);
		btnIconO.addMouseListener(MouseClick);
		btnIconCone.addMouseListener(MouseClick);
		btnIconText.addMouseListener(MouseClick);
		lblBoard.addMouseListener(MouseClick);
		
		listIcon = new ArrayList<JButton>();
		listIcon.add(btnIconX);
		listIcon.add(btnIconO);
		listIcon.add(btnIconCone);
		listIcon.add(btnIconText);
		
		

	}
	private BufferedImage getIconByName(String Icon) throws IOException
	{
		if(Icon == "IconX")
			return ImageIO.read(new File(c_SrcFolder + "IconX.png"));
		if(Icon == "IconO")
			return ImageIO.read(new File(c_SrcFolder + "IconO.png"));
		if(Icon == "IconCone")
			return ImageIO.read(new File(c_SrcFolder + "IconCone.png")); 
		if(Icon == "IconText")
			return ImageIO.read(new File(c_SrcFolder + "IconText.png"));
		return null;
	}

	public void DrawIconToBoard(String imageName, Point imagePoint) throws IOException
	{
		var icon = getIconByName(imageName);
		var g = lblBoard.getGraphics();
        g.drawImage(icon, imagePoint.x, imagePoint.y, 30, 30, null);

	}
	
	
	

}
