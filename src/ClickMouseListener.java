 import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClickMouseListener implements java.awt.event.MouseListener {

	private String IconSelected;
	private TraningSet FrameToDraw;
	
	
	public ClickMouseListener(TraningSet frame)
	{
		super();
		this.FrameToDraw = frame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() instanceof JLabel)
		{
			if (!IconSelected.isEmpty())
			{
				try {
					FrameToDraw.DrawIconToBoard(IconSelected, new Point(e.getX(),e.getY()));
					IconSelected = null;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return;
		}
		
		String btnClick = ((JButton)e.getSource()).getText();
		
		if(btnClick == "IconO" || btnClick == "IconX" || btnClick == "IconCone" || btnClick == "btnIconText" )
			IconSelected = btnClick;
			
		System.out.println(((JButton)e.getSource()).getText());

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
