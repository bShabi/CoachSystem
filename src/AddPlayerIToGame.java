import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class AddPlayerIToGame extends JFrame {

	private JPanel pnlInsertPlayers;
	private JComboBox[] cbListPlayersInGame;
	private JSpinner[] spinerRating;
	private DB DataBase;
	private DefaultComboBoxModel[] model;



	public AddPlayerIToGame(int numOfPlayer) {
		initComponets();
		
		cbListPlayersInGame = new JComboBox[numOfPlayer];
		spinerRating = new JSpinner[numOfPlayer];
		model = new DefaultComboBoxModel[numOfPlayer];
		SpinnerListener handler = new SpinnerListener();
		DataBase = DB.getDatabaseMg();
		for(int i=0,Space=0;i<cbListPlayersInGame.length;i++,Space+=30)
		{
			cbListPlayersInGame[i] = new JComboBox<Object>(); 
			cbListPlayersInGame[i].setBounds(30, 40+Space, 90, 20);
			model[i] = new DefaultComboBoxModel( DataBase.GetPlayerByNameToCB() );
			cbListPlayersInGame[i].setModel(model[i]);
			pnlInsertPlayers.add(cbListPlayersInGame[i]);
			
			spinerRating[i] = new JSpinner();
			spinerRating[i].setBounds(150, 40+Space, 90, 20);
			spinerRating[i].addChangeListener(handler);
			pnlInsertPlayers.add(spinerRating[i]);
			

			

		}
		
//		spinerRating = new JSpinner();
//		spinerRating.setBounds(181, 38, 64, 20);
//		pnlInsertPlayers.add(spinerRating);
	}
	public String[] GetPlayerByNameToCB()
	{
		var allplayers = DataBase.getPlayer();
		var numOfPlayers = allplayers.size();
		var playersNames = new String[numOfPlayers];
		for(int i=0;i<numOfPlayers;i++)
		{
			playersNames[i] = "(" + allplayers.get(i).getPlayerID() + ")" + " " + allplayers.get(i).getName();
		}
		return playersNames;
	}


	private void initComponets() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 431, 484);
		pnlInsertPlayers = new JPanel();
		pnlInsertPlayers.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlInsertPlayers);
		pnlInsertPlayers.setLayout(null);		
	}
	class SpinnerListener implements ChangeListener {
		  public void stateChanged(ChangeEvent evt) {
			    int min = 1;
			    int max = 10;
			    int step = 1;
			    var spinner = ((JSpinner)evt.getSource());
			    int selectedValue =(Integer)spinner.getValue(); 
			    SpinnerModel value = new SpinnerNumberModel(selectedValue, min, max, step);
			    ((JSpinner)evt.getSource()).setModel(value);
		  }
		}
}
