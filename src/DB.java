import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class DB {
	private Statement stmt;
	private int gameFixure;
	private static DB databaseMg;

	public DB() {
		try {
			String dbName = "CoachSystem";
			String dbUserName = "root";
			String dbPassword = "Password";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.64.2:3306/" + dbName + "?user="
					+ dbUserName + "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8");
			stmt = con.createStatement();
			// getPlayers();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public boolean Login(String sql) throws SQLException {
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next())
				return false;
			else
				return true;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

	///////// ADD , Remove , Get Player/////////
	public boolean AddPlayer(Player player) throws SQLException {
		try {
			String sql = "insert into Players(PlayerID,Name,Age,Position) VALUES (" + player.getPlayerID() + ",'"
					+ player.getName() + "'," + player.getAge() + ",'" + player.getPosition() + "')";
			System.out.print(sql);
			if (stmt.executeUpdate(sql) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean RemovePlayer(int playerID) {

		try {
			String sql = "DELETE FROM Players WHERE PlayerID = " + playerID;
			System.out.println(sql);
			if (stmt.executeUpdate(sql) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

	public ArrayList<Player> getPlayer() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		try {
			String sql = "Select * from Players";
			ResultSet rs = getResultSet(sql);
			Player player;
			while (rs.next()) {
				player = new Player(rs.getInt("PlayerID"), rs.getString("Name"), rs.getString("Position"),
						rs.getFloat("Age"));
				allPlayers.add(player);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return allPlayers;
	}

	public ArrayList<Game> getGames() {
		ArrayList<Game> allGame = new ArrayList<Game>();
		try {
			String sql = "Select * from Games";
			ResultSet rs = getResultSet(sql);
			Game game;
			while (rs.next()) {
				game = new Game(rs.getInt("GameID"), rs.getString("Team_opponent"), rs.getInt("GoalHome"),
						rs.getInt("GoalGuest"), rs.getDate("Date"));
				allGame.add(game);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return allGame;
	}

	/////// ADD,Remove,Get Game From DB ////////
	public boolean AddGame(Game game) throws SQLException {
		try {
			String sql = "insert into Games(GameID,Team_opponent,GoalHome,GoalGuest,Date) VALUES(" + game.getGameID()
					+ ",'" + game.getTeam_Opponent() + "'," + game.getGoalHome() + "," + game.getGoalGuest() + ",'"
					+ game.getDate() + "')";
			System.out.print(sql);
			if (stmt.executeUpdate(sql) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean InsertPlayersGradesToDB(int gameID, Map<Integer, Integer> DicDetails) throws SQLException {
		try {
			boolean isSuceeded = true;
			for (Map.Entry<Integer, Integer> PlayerInfo : DicDetails.entrySet()) {
				String sql = "insert into PlayerInGame(GameID,PlayerID,Grade) VALUES(" + gameID + ","
						+ PlayerInfo.getKey() + "," + PlayerInfo.getValue() + ")";
				System.out.print(sql);
				if (stmt.executeUpdate(sql) == 1)
					isSuceeded &= true;
				else
					isSuceeded &= false;
			}
			return isSuceeded;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static DB getDatabaseMg() {
		if (databaseMg == null)
			databaseMg = new DB();

		return databaseMg;
	}

	public boolean RemoveGame(int GameID) {
		try {
			String sql = "DELETE FROM Games WHERE GameID = '" + GameID + "'";
			System.out.println(sql);
			if (stmt.executeUpdate(sql) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

	public boolean isValidPlayer(int playerID) {
		try {
			String sql = "select PlayerID from Players where PlayerID ='" + playerID + "'";
			if (getResultSet(sql).next())
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public ResultSet getGame() throws SQLException {
		// ResultSet rs= stmt.executeQuery("select * from Games");
		return getResultSet("select * from Games");
	}

	public ResultSet getPlayers() throws SQLException {
		return getResultSet("select * from Players");
	}

	public ResultSet getResultSet(String query) throws SQLException {
		return stmt.executeQuery(query);
	}


	public TableModel buildTableModel(ResultSet query) throws SQLException {
		ResultSetMetaData metaData = (ResultSetMetaData) query.getMetaData();
		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (query.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(query.getObject(columnIndex));
			}
			data.add(vector);
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false; // To change body of generated methods, choose Tools | Templates.
			}

		};

		return model;
	}

	public Map<Integer, Integer> GetGradePlayerByGame(int gameID) {
		try {
			String sql = "select PlayerID,Grade from PlayerInGame where GameID =" + gameID;
			ResultSet rs = getResultSet(sql);
			Map<Integer, Integer> dicResult = new HashMap<Integer, Integer>();
			while (rs.next()) {
				dicResult.put(rs.getInt("PlayerID"), rs.getInt("Grade"));
			}
			return dicResult;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public String[] GetPlayerByNameToCB() {
		var allplayers = getPlayer();
		var numOfPlayers = allplayers.size();
		var playersNames = new String[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			playersNames[i] = "(" + allplayers.get(i).getPlayerID() + ")" + " " + allplayers.get(i).getName();
		}
		return playersNames;
	}

	public int getNextGameNum() {
		var games = getGames();
		gameFixure = games.size() + 1;
		while (games.stream().anyMatch(game -> game.getGameID() == gameFixure)) {
			gameFixure++;
		}
		return gameFixure;
	}

}
