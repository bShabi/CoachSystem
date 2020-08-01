import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

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
	public final String c_SrcFolder = "./img/";
	private ArrayList<JButton> listIcon;
	private JLabel lblBoard;
	private JPanel contentPane;
	private Point pointToDrawIcon;
    private String IconToDraw;
	private BufferedImage m_BoardImage;
	
	
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
		this.contentPane.setBackground(new Color(224, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
		
		//set icon to Button
		ImageIcon icon_O = new ImageIcon(c_SrcFolder + "IconO.png");
		ImageIcon icon_X = new ImageIcon(c_SrcFolder + "IconX.png");
		ImageIcon icon_Cone = new ImageIcon(c_SrcFolder + "IconCone.png"); 
		ImageIcon icon_Text = new ImageIcon(c_SrcFolder + "IconText.png"); 
		ImageIcon icon_Arrow = new ImageIcon(c_SrcFolder + "arrow.png");
		ImageIcon icon_Arrow2 = new ImageIcon(c_SrcFolder + "arrow2.png");
		
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
		lblBoard.addMouseListener(MouseClick);
		
		listIcon = new ArrayList<JButton>();
		listIcon.add(btnIconX);
		listIcon.add(btnIconO);
		listIcon.add(btnIconCone);

		
		JButton btnIconArrow = new JButton("Arrow");
		btnIconArrow.setIcon(icon_Arrow);
		btnIconArrow.setBounds(1105, 100, 85, 57);
		pnlBoard.add(btnIconArrow);
		
		JButton btnArrow2 = new JButton("Arrow2");
		btnArrow2.setIcon(icon_Arrow2);
		btnArrow2.setBounds(1105, 225, 85, 57);
		pnlBoard.add(btnArrow2);
		
		JButton btnSaveImg = new JButton("Save");
		btnSaveImg.setBounds(1093, 369, 97, 57);
		pnlBoard.add(btnSaveImg);
		
		JButton btnClear = new JButton("Clear Board");
		btnClear.setBounds(1093, 470, 97, 72);
		pnlBoard.add(btnClear);
		btnIconArrow.addMouseListener(MouseClick);
		btnArrow2.addMouseListener(MouseClick);
		btnSaveImg.addMouseListener(MouseClick);
		btnClear.addMouseListener(MouseClick);
		
		// Set image with lblBoard background
		m_BoardImage = new BufferedImage(lblBoard.getWidth(), lblBoard.getHeight(), BufferedImage.TYPE_INT_ARGB);
		lblBoard.printAll(m_BoardImage.getGraphics());
	}
	private BufferedImage getIconByName(String Icon) throws IOException
	{
		if(Icon == "IconX")
			return ImageIO.read(new File(c_SrcFolder + "IconX.png"));
		if(Icon == "IconO")
			return ImageIO.read(new File(c_SrcFolder + "IconO.png"));
		if(Icon == "IconCone")
			return ImageIO.read(new File(c_SrcFolder + "IconCone.png")); 
		return null;
	}

	public void DrawIconToBoard(String imageName, Point imagePoint) throws IOException
	{
		var icon = getIconByName(imageName);
		var boardGraphics = lblBoard.getGraphics();
		boardGraphics.drawImage(icon, imagePoint.x, imagePoint.y, 30, 30, null);
		
		var imageGraphics = m_BoardImage.getGraphics();
		imageGraphics.drawImage(icon, imagePoint.x, imagePoint.y, 30, 30, null);
	}
	
	public void DrawArrowToBoard(Boolean isDashedArrow, Point firstPoint, Point secondPoint) throws IOException
	{
		
		var g = lblBoard.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		float[] dashSpaces = null;
		
		// Set Stroke as dashed
		if(isDashedArrow)
		 dashSpaces = new float[]{9};
		
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashSpaces , 0);
        g2d.setStroke(dashed);
		
        g2d.drawLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
        
        var imageGraphics = (Graphics2D) m_BoardImage.getGraphics();
		imageGraphics.drawLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);

	}
	
	public void SaveBoardToImg()
	{	 
		 // create instance of Random class 
	     Random rand = new Random(); 
	  
	     // Generate random integers in range 0 to 999 
	     int rndNum = rand.nextInt(1000); 
	        
		 File outputfile = new File("board"+ rndNum +".png");
		 try {
			ImageIO.write(m_BoardImage, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 ClearBoard();
	}
	
	public void ClearBoard()
	{
        lblBoard.repaint();
        lblBoard.printAll(m_BoardImage.getGraphics());
 

	}

}
