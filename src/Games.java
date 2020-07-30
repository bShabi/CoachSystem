import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetMetaData;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class Games extends JFrame {

	
	final  String DATE_FORMAT = "yyyy-MM-dd";
	private JPanel JFrameTeamGame;
	private JPanel panelAddGame;
	private JPanel panelMyGame;
	private JTextField textDate;
	private JTextField textTeamOpponent;
	private JTextField textMy_Score;
	private JTextField textOpponent_Scroe;
	private JButton btnSubbmit;
	private JScrollPane scrollPane;
	private JTable tableMyGame;
	private JButton btnExit;
	private JButton btnTeamSquad;
	private JButton btnTraningSet;
	private JSpinner spRangePlayers;
	private JSpinner spFixure;
	private DB DataBase;
	private AddPlayerIToGame addPlayerToGameFrm;
	

	
	
	public Games() {
		DataBase = DB.getDatabaseMg();
		initComponets();
		spRangePlayers.addChangeListener(new SpinnerListener());
		Handler handler = new Handler();
		btnSubbmit.addActionListener(handler);
		btnExit.addActionListener(handler);
		
		
	}

	private void initComponets() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 800, 600);
		JFrameTeamGame = new JPanel();
		JFrameTeamGame.setBackground(new Color(255, 255, 224));
		JFrameTeamGame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JFrameTeamGame);
		JFrameTeamGame.setLayout(null);
		
		///////Panel Add Game//////
		panelAddGame = new JPanel();
		panelAddGame.setBackground(new Color(255, 255, 255));
		panelAddGame.setBounds(10, 83, 258, 369);
		JFrameTeamGame.add(panelAddGame);
		panelAddGame.setLayout(null);
		
		JLabel lblAddGame = new JLabel("Add Game");
		lblAddGame.setForeground(new Color(0, 0, 128));
		lblAddGame.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAddGame.setBackground(new Color(0, 0, 128));
		lblAddGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddGame.setBounds(51, 11, 116, 37);
		panelAddGame.add(lblAddGame);
		
		JLabel lblTeamOppen = new JLabel("Team opponent:\t");
		lblTeamOppen.setBounds(10, 124, 116, 29);
		panelAddGame.add(lblTeamOppen);
		
		JLabel lblScoreMyTeam = new JLabel("Score myTeam:");
		lblScoreMyTeam.setBounds(10, 153, 107, 29);
		panelAddGame.add(lblScoreMyTeam);
		
		JLabel lblScoreOpponet = new JLabel("Score opponent:");
		lblScoreOpponet.setBounds(10, 193, 97, 23);
		panelAddGame.add(lblScoreOpponet);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 104, 97, 14);
		panelAddGame.add(lblDate);
		
		textDate = new JTextField();
		textDate.setBounds(125, 101, 96, 20);
		panelAddGame.add(textDate);
		textDate.setColumns(10);
		
		textTeamOpponent = new JTextField();
		textTeamOpponent.setBounds(125, 128, 96, 20);
		panelAddGame.add(textTeamOpponent);
		textTeamOpponent.setColumns(10);
		
		textMy_Score = new JTextField();
		textMy_Score.setBounds(125, 164, 96, 20);
		panelAddGame.add(textMy_Score);
		textMy_Score.setColumns(10);
		
		textOpponent_Scroe = new JTextField();
		textOpponent_Scroe.setBounds(125, 194, 96, 20);
		panelAddGame.add(textOpponent_Scroe);
		textOpponent_Scroe.setColumns(10);
		
		btnSubbmit = new JButton("Subbmit");
		btnSubbmit.setBounds(132, 287, 89, 23);
		panelAddGame.add(btnSubbmit);
		
		JLabel lblNewLabel_5 = new JLabel("Num of Player");
		lblNewLabel_5.setBounds(10, 250, 89, 21);
		panelAddGame.add(lblNewLabel_5);
		
		

		spRangePlayers = new JSpinner();
		spRangePlayers.setBounds(153, 250, 68, 20);
		panelAddGame.add(spRangePlayers);
		
		JLabel lblFixure = new JLabel("Fixrue");
		lblFixure.setHorizontalAlignment(SwingConstants.CENTER);
		lblFixure.setBounds(10, 59, 48, 14);
		panelAddGame.add(lblFixure);
		
		spFixure = new JSpinner();
		spFixure.setModel(new SpinnerNumberModel(DataBase.getGames().size()+1,null,null,1));
		spFixure.setEnabled(false);
		spFixure.setBounds(153, 59, 30, 20);
		panelAddGame.add(spFixure);
		//setRangePlayer();
		///END OF Panel add game/////
		
		///Start of Panel my Game ///
		panelMyGame = new JPanel();
		panelMyGame.setBackground(new Color(255, 255, 255));
		panelMyGame.setBounds(278, 83, 500, 369);
		JFrameTeamGame.add(panelMyGame);
		panelMyGame.setLayout(null);
		
		JLabel lblMyGame = new JLabel("My Games");
		lblMyGame.setForeground(new Color(0, 0, 128));
		lblMyGame.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblMyGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyGame.setBounds(163, 11, 111, 26);
		panelMyGame.add(lblMyGame);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 462, 270);
		panelMyGame.add(scrollPane);
		
		//Table of my GamesJTable table = new JTable(buildTableModel(rs));
		try {
			tableMyGame = new JTable(DataBase.buildTableModel(DataBase.getGame()));
			tableMyGame.setEnabled(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(tableMyGame);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(73, 474, 163, 49);
		JFrameTeamGame.add(btnExit);
		
		btnTeamSquad = new JButton("Team Squad");
		btnTeamSquad.setBounds(361, 474, 154, 49);
		JFrameTeamGame.add(btnTeamSquad);
		
		btnTraningSet = new JButton("Traning Set");
		btnTraningSet.setBounds(614, 474, 146, 49);
		JFrameTeamGame.add(btnTraningSet);
	}





	class SpinnerListener implements ChangeListener {
		  public void stateChanged(ChangeEvent evt) {
			    int min = 1;
			    int max = DataBase.getPlayer().size();
			    int step = 1;
			    var spinner = ((JSpinner)evt.getSource());
			    int selectedValue =(Integer)spinner.getValue(); 
			    SpinnerModel value = new SpinnerNumberModel(selectedValue, min, max, step);
			    ((JSpinner)evt.getSource()).setModel(value);
			  
		  }
		}
	class Handler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String btnClick = e.getActionCommand();
			
			if(btnClick == "Exit")
				System.exit(1);
			
			if(btnClick == "Subbmit")
			{
				try 
				{
				String date = textDate.getText();
				String teamOpponent = textTeamOpponent.getText();
				int myScore = Integer.valueOf(textMy_Score.getText());
				int OpponentScore = Integer.valueOf(textOpponent_Scroe.getText());
				int fixure = Integer.valueOf(spFixure.getValue().toString());
				
				
				
				if(teamOpponent.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please insert Team Opponent");
					return;
				}
				if(myScore <0)
				{
					JOptionPane.showMessageDialog(null,"Please insert Currect Score ");
					return;
				}
				if(OpponentScore <0)
				{
					JOptionPane.showMessageDialog(null,"Please insert Currect Score ");
					return;
				}
				if(!isValidDate(date))
				{
					JOptionPane.showMessageDialog(null,"Please insert Currect Date yyyy-MM-dd ");
					return;
				}
				//// Since now can you sure that props are currect
					addPlayerInGame(Integer.valueOf(spRangePlayers.getValue().toString()));
					if(DataBase.AddGame(new Game(fixure,teamOpponent,myScore,OpponentScore,Date.valueOf(date))))
					{
						System.out.println("Success");
						tableMyGame.setModel(DataBase.buildTableModel(DataBase.getGame()));
						spFixure.setModel(new SpinnerNumberModel(DataBase.getGames().size()+1,null,null,1));
						ClearAllTextfield();

					}
				
				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"Please insert Currect Values -(Example) Date (YYYY-MM-DD) \n Team Opponent: Macabi Tel Aviv \n myScore: 1 scoreOpponent: 0 ");
					e1.printStackTrace();
				}
			}
		}
		private void ClearAllTextfield() {

			textDate.setText(null);
			textTeamOpponent.setText(null);
			textMy_Score.setText(null);
			textOpponent_Scroe.setText(null);
			
		}
		private boolean isValidDate(String date)
		{
			try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
				return true;
			}catch(Exception e1) {
				e1.printStackTrace();
				return false;
			}
		}
		private void addPlayerInGame(int numOfPlayer) {
			if(numOfPlayer<=0)
			{
				JOptionPane.showMessageDialog(null,"Please set Number of player whose take part in Game");
				return;
			}
//			if(addPlayerToGameFrm != null && addPlayerToGameFrm.isVisible())
//			{
				addPlayerToGameFrm = new AddPlayerIToGame(numOfPlayer);
				addPlayerToGameFrm.setVisible(true);
			
		}
		
	}
}
