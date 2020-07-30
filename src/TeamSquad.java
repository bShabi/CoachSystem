import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class TeamSquad extends JFrame {
	public enum posstion {GK, CB, RB, LB, MC, DM, LW, RW, ST};
	private JPanel JFrameTeamSqouad;
	private JPanel panelAddPlayer;
	private JPanel panelPlayers;
	private JPanel panelDelPlayer;
	private JScrollPane scrollPane;
	private JTextField textName;
	private JTextField textAge;
	private JTextField textID;
	private JComboBox cbListPlayer;
	private JComboBox cbPosition;
	private JButton btnDel;
	private JButton btnSubbmit;
	private JLabel lblAddPlayer;
	ArrayList<String> nameOfPlayers;
	private DB DataBase;
	private  JTable tableOfPlayer;
	private DefaultComboBoxModel model;
	private Hendler hendler;

	
	
	public TeamSquad() {
		initComponets();
		//insertToTable();
		
		hendler = new Hendler();
		btnSubbmit.addActionListener(hendler);
		btnDel.addActionListener(hendler);
		//btnRefresh.addActionListener(hendler);

	}
	private void initComponets()  {
		DataBase = DB.getDatabaseMg();
		setBounds(100, 100, 800, 600);
		this.JFrameTeamSqouad = new JPanel();
		this.JFrameTeamSqouad.setBackground(new Color(255, 255, 224));
		this.JFrameTeamSqouad.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(JFrameTeamSqouad);
		this.JFrameTeamSqouad.setLayout(null);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panelAddPlayer = new JPanel();
		panelAddPlayer.setBackground(new Color(255, 255, 224));
		panelAddPlayer.setBounds(10, 47, 272, 311);
		JFrameTeamSqouad.add(panelAddPlayer);
		panelAddPlayer.setLayout(null);
		
		lblAddPlayer = new JLabel("Add Player");
		lblAddPlayer.setForeground(new Color(25, 25, 112));
		lblAddPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPlayer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblAddPlayer.setBounds(69, 21, 130, 25);
		panelAddPlayer.add(lblAddPlayer);
		
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(22, 71, 57, 25);
		panelAddPlayer.add(lblNewLabel);
		
		textID = new JTextField();
		textID.setBounds(99, 71, 96, 20);
		panelAddPlayer.add(textID);
		
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName.setBounds(22, 96, 67, 25);
		panelAddPlayer.add(lblFullName);
		
		textName = new JTextField();
		textName.setBounds(99, 98, 96, 20);
		panelAddPlayer.add(textName);
		textName.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setBounds(22, 133, 48, 14);
		panelAddPlayer.add(lblAge);
		
		textAge = new JTextField();
		textAge.setBounds(103, 130, 96, 20);
		panelAddPlayer.add(textAge);
		textAge.setColumns(10);
		
		JLabel lblPostion = new JLabel("Position");
		lblPostion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPostion.setBounds(22, 170, 57, 14);
		panelAddPlayer.add(lblPostion);
		
		cbPosition = new JComboBox();
		cbPosition.setModel(new DefaultComboBoxModel(posstion.values()));
		cbPosition.setMaximumRowCount(12);
		cbPosition.setBounds(103, 166, 96, 22);
		panelAddPlayer.add(cbPosition);
		
		btnSubbmit = new JButton("Subbmit");
		btnSubbmit.setBounds(103, 210, 96, 23);
		panelAddPlayer.add(btnSubbmit);
		

		////////////END OF Panel ADD  Player////////
		
		panelPlayers = new JPanel();
		panelPlayers.setBackground(new Color(255, 255, 224));
		panelPlayers.setBounds(292, 47, 474, 456);
		JFrameTeamSqouad.add(panelPlayers);
		panelPlayers.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 47, 422, 345);
		panelPlayers.add(scrollPane);
		

		try {
			tableOfPlayer = new JTable(DataBase.buildTableModel(DataBase.getPlayers()));
			tableOfPlayer.setEnabled(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(tableOfPlayer);
		
		JLabel lblTeamSqouad = new JLabel("My Team");
		lblTeamSqouad.setBounds(113, 1, 236, 35);
		panelPlayers.add(lblTeamSqouad);
		lblTeamSqouad.setForeground(new Color(0, 0, 128));
		lblTeamSqouad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblTeamSqouad.setHorizontalAlignment(SwingConstants.CENTER);
		
		/////END OF Panel Players ////////
		
		panelDelPlayer = new JPanel();
		panelDelPlayer.setBackground(new Color(255, 255, 224));
		panelDelPlayer.setBounds(10, 369, 274, 136);
		JFrameTeamSqouad.add(panelDelPlayer);
		panelDelPlayer.setLayout(null);
		
		JLabel lblRemovePlayer = new JLabel("Remove Player");
		lblRemovePlayer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblRemovePlayer.setForeground(new Color(25, 25, 112));
		lblRemovePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemovePlayer.setBounds(34, 11, 158, 14);
		panelDelPlayer.add(lblRemovePlayer);
		
		JLabel lblSelecetPlayer = new JLabel("Select Player");
		lblSelecetPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecetPlayer.setBounds(10, 46, 108, 14);
		panelDelPlayer.add(lblSelecetPlayer);
		
		
		model = new DefaultComboBoxModel(getComboBoxFromDB() );
		cbListPlayer = new JComboBox(model);
		cbListPlayer.setBounds(128, 42, 121, 22);
		panelDelPlayer.add(cbListPlayer);

		btnDel = new JButton("Remove"); 
		btnDel.setBounds(138, 84, 89, 23);
		panelDelPlayer.add(btnDel);
		

	}

	
	private Object[] getComboBoxFromDB() {
		return DataBase.GetPlayerByNameToCB();
	}
	public void deletePlayerFromTable(String playerName) {
		ArrayList<Player> listOfPlayer = DataBase.getPlayer();
		DefaultTableModel mode = (DefaultTableModel)tableOfPlayer.getModel();
		Object[] row  = new Object[3];
		for(int i=0;i<listOfPlayer.size();i++)
		{
			if(tableOfPlayer.getModel().getValueAt(i, 0)==playerName)
			{
				mode.removeRow(i);
			}
			
		}
		
		
	}

	public void ClearInsert()
	{
		textID.setText(null);
		textName.setText(null);
		textAge.setText(null);
		
	}
	
	
	
	class Hendler implements ActionListener
	{
		
		public int getPlayerId(String playerString)
		{
		      
			return Integer.valueOf(playerString.replaceAll("\\D+",""));

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String btnClick = e.getActionCommand();
			
			if(btnClick == "Remove")
			{
				var getCombobox=cbListPlayer.getSelectedItem().toString();
				int playerId = getPlayerId(getCombobox);
			
				if(DataBase.RemovePlayer(playerId))
				{
					System.out.println("Success");
					
			    	model = new DefaultComboBoxModel( DataBase.GetPlayerByNameToCB() );
			    	cbListPlayer.setModel(model);
			    	deletePlayerFromTable(cbListPlayer.getSelectedItem().toString());
					try {
						tableOfPlayer.setModel(DataBase.buildTableModel(DataBase.getPlayers()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else 
					System.out.println("not Success");

			}
			if(btnClick == "Subbmit")
			{	
				addPlayerToDB();
				try {
					tableOfPlayer.setModel(DataBase.buildTableModel(DataBase.getPlayers()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(btnClick == "Refresh")
			{
				
			}
		}

		private void addPlayerToDB() {

			try {		
				int PlayerID = Integer.valueOf(textID.getText());
				String PlayerName = textName.getText();
				String Position = cbPosition.getSelectedItem().toString();
				Float Age = Float.valueOf(textAge.getText());
				
				if(PlayerID<=0)
				{
					JOptionPane.showMessageDialog(null,"Please insert Currect ID");
					return;
				}
				if(Age<=0)
				{
					JOptionPane.showMessageDialog(null,"Please insert Currect Age");
					return;
				}
				if(PlayerName.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please insert Name player");
					return;
				}
					
				if(!(DataBase.isValidPlayer(PlayerID)))
				{
					JOptionPane.showMessageDialog(null,"Please insert another ID");
					return;
				}
					
				if(DataBase.AddPlayer(new Player(PlayerID,PlayerName,Position,Age)))
				{
					JOptionPane.showMessageDialog(null,"Success");
			    	model = new DefaultComboBoxModel( DataBase.GetPlayerByNameToCB() );
			    	cbListPlayer.setModel(model);
					ClearInsert();
				}
				else 
					JOptionPane.showMessageDialog(null,"Not Success");
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"Please insert Currect Values");
				e1.printStackTrace();
			}
		}
		
	}
}
