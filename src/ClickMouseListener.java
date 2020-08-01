 import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ClickMouseListener implements java.awt.event.MouseListener {

	private String IconSelected;
	private TraningSet FrameToDraw;
	private Point firstArrowPoint;
	
	
	public ClickMouseListener(TraningSet frame)
	{
		super();
		this.FrameToDraw = frame;
		this.IconSelected = "";
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Point clickedPoint = new Point(e.getX(),e.getY());
		/* Is click on board */
		if(e.getSource() instanceof JLabel)
		{
			if (!IconSelected.isEmpty())
			{
				try {
					if(IconSelected == "Arrow" || IconSelected == "Arrow2")
					{
						// If not the first click for arrow
						if(firstArrowPoint != null){
							// Draw Arrow
							FrameToDraw.DrawArrowToBoard(IconSelected == "Arrow2", firstArrowPoint, clickedPoint);
							
							// Init all drawing arrow related props
							firstArrowPoint = null;
							IconSelected = "";
						}
						else {
							// Set first Point for Arrow
							firstArrowPoint = clickedPoint;
						}
					}
					else
					{
						// Draw icon on board
						FrameToDraw.DrawIconToBoard(IconSelected, clickedPoint);
						
						// Set drawing icon selected prop
						IconSelected = "";
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return;
		}
		
		String btnClick = ((JButton)e.getSource()).getText();
		
		if(btnClick == "Clear Board")
		{
			FrameToDraw.ClearBoard();
			return;
		}
		if(btnClick == "Save")
		{
			FrameToDraw.SaveBoardToImg();
			return;
		}
		
		//if(btnClick == "IconO" || btnClick == "IconX" || btnClick == "IconCone" || btnClick == "btnIconText")
		IconSelected = btnClick;
		
		if(IconSelected == "Arrow" || IconSelected == "Arrow2")
			JOptionPane.showMessageDialog(null, "Click on two point in board");
		
		


	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
