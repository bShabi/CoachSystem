package database;
import default package Player
import java.sql.*;
import java.util.ArrayList;





public class DB {
	private Statement stmt;
	
	
	public DB() {
		 try{
			String dbName = "CoachSystem";
			String dbUserName = "root";
			String dbPassword = "Pa$$word";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://192.168.64.2:3306/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8");
			stmt =con.createStatement();
			getPlayers();
			 }catch(Exception e){System.out.println(e);}
	
	}
	 public boolean Login(String sql) throws SQLException{
		 try {
			 ResultSet rs=stmt.executeQuery(sql);
			 if(!rs.next())
				return false;
			else
				return true;
			 
		  } catch(Exception e){System.out.println(e);}
		return false;
		 
	 }

	 public boolean AddPlayer(ArrayList<String> listNewPlayer) throws SQLException{
		 Player player = new Player();
		 try {
			 String sql = "insert into Players(Name,Age,Position) VALUES ('"+listNewPlayer.get(0)+ "',"+ Integer.parseInt(listNewPlayer.get(1))+",'"+listNewPlayer.get(2) +"')";
			 System.out.print(sql);
			 if (stmt.executeUpdate(sql) == 1) 	
				 return true;
			 else 
				 return false;
		 }catch(Exception e) {System.out.println(e);}
		 return false;
	 }
	 
	 public void getPlayers() throws SQLException
	 {
		 String sql = "Select * from Players";
		 ResultSet rs=stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("PlayerID");
            String name = rs.getString("Name");
            int age = rs.getInt("Age");
            String Posstion = rs.getString("Position");
            System.out.println(id + "\t" + name +
                               "\t" + age + "\t" + Posstion +
                               "\n");
        }
	 }
	 
	 
//	 public ResultSet getPlayers(String sql) throws SQLException
//	 {
//         ResultSet rs = stmt.executeQuery(sql);
//         return rs;
//	 }

}


