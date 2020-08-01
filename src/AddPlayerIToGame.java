import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.Data;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class AddPlayerIToGame extends JFrame {

	private JPanel pnlInsertPlayers;
	private JComboBox[] cbListPlayersInGame;
	private JSpinner[] spinerRating;
	private DB DataBase;
	private DefaultComboBoxModel[] model;
	private int m_NumberOfPlayer;
	private int m_Fixure;

	public AddPlayerIToGame(int numOfPlayer,int fixure) {
		initComponets();
		initSubmitButton();
		this.m_NumberOfPlayer = numOfPlayer;
		this.m_Fixure = fixure;
		cbListPlayersInGame = new JComboBox[m_NumberOfPlayer];
		spinerRating = new JSpinner[m_NumberOfPlayer];
		model = new DefaultComboBoxModel[m_NumberOfPlayer];
		SpinnerListener handler = new SpinnerListener();
		DataBase = DB.getDatabaseMg();
		var AllPlayers =  DataBase.GetPlayerByNameToCB();
		for(int i=0,Space=0;i<cbListPlayersInGame.length;i++,Space+=30)
		{
			cbListPlayersInGame[i] = new JComboBox<Object>(); 
			cbListPlayersInGame[i].setBounds(30, 40+Space, 90, 20);
			model[i] = new DefaultComboBoxModel(AllPlayers);
			if(AllPlayers.length>i)
				model[i].setSelectedItem(AllPlayers[i]);
			cbListPlayersInGame[i].setModel(model[i]);
			pnlInsertPlayers.add(cbListPlayersInGame[i]);
			
			spinerRating[i] = new JSpinner();
			spinerRating[i].setBounds(150, 40+Space, 90, 20);
			spinerRating[i].addChangeListener(handler);
			pnlInsertPlayers.add(spinerRating[i]);
		}

	}
	
	public AddPlayerIToGame(Map<Integer,Integer> PlayerDic)
	{
		initComponets();
		initExitButton();
		this.m_NumberOfPlayer = PlayerDic.size();
		cbListPlayersInGame = new JComboBox[m_NumberOfPlayer];
		spinerRating = new JSpinner[m_NumberOfPlayer];
		model = new DefaultComboBoxModel[m_NumberOfPlayer];
		DataBase = DB.getDatabaseMg();
		var lstPlayers = PlayerDic.keySet().toArray();
		var lstGrades = PlayerDic.values().toArray();
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		try {
			allPlayers = DataBase.getPlayer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0,Space=0;i<m_NumberOfPlayer;i++,Space+=30)
		{
			cbListPlayersInGame[i] = new JComboBox<Object>(); 
			cbListPlayersInGame[i].setBounds(30, 40+Space, 90, 20);
			Player playerById = GetPlayerById(Integer.valueOf(lstPlayers[i].toString()), allPlayers);
			if(playerById == null)
				continue;
			
			String playerName = "(" + playerById.getPlayerID() + ")" + " " + playerById.getName();
			model[i] = new DefaultComboBoxModel(new String[] {playerName});
			cbListPlayersInGame[i].enable(false);
			cbListPlayersInGame[i].setModel(model[i]);
			pnlInsertPlayers.add(cbListPlayersInGame[i]);
			
			spinerRating[i] = new JSpinner();
			spinerRating[i].setBounds(150, 40+Space, 90, 20);
			spinerRating[i].setValue(lstGrades[i]);
			spinerRating[i].setEnabled(false);
			pnlInsertPlayers.add(spinerRating[i]);
		}

	}
	
	public Player GetPlayerById(int playerId, ArrayList<Player> playersArray)
	{
		for (Player player : playersArray) {
			if(player.getPlayerID() == playerId)
				return player;
		}
		
		return null;
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
	private void initSubmitButton()
	{
		JButton btnAddGrades = new JButton("Submit");
		btnAddGrades.setBounds(150, 383, 114, 36);
		pnlInsertPlayers.add(btnAddGrades);
		btnAddGrades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "Submit")
				{
					Map<Integer, Integer> dicResults = new HashMap<Integer, Integer>();
					for (int i = 0; i < m_NumberOfPlayer; i++) {
						String namePlayer = cbListPlayersInGame[i].getSelectedItem().toString();
						if(dicResults.containsKey(namePlayer))
						{
							JOptionPane.showMessageDialog(null,"Please avoid duplicate names");
							return;
						}
						dicResults.put(Integer.valueOf(namePlayer.replaceAll("\\D+","")), Integer.valueOf(spinerRating[i].getValue().toString()));	
					}
					
					try {
						Boolean isSucceeded = DataBase.InsertPlayersGradesToDB(m_Fixure, dicResults);
						if(!isSucceeded)
						{
							JOptionPane.showMessageDialog(null,"Fail to insert data to DB");
							return;
						}
							
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Error! - Fail to insert data to DB");
						e1.printStackTrace();
						return;
					}
					JOptionPane.showMessageDialog(null,"Success!");
					dispose();
				}
				
			}
		});
	}
	
	private void initExitButton()
	{
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(150, 383, 114, 36);
		pnlInsertPlayers.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "Exit")
				{
					dispose();
				}
			}
		});
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
