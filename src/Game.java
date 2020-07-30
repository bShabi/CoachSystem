import java.sql.Date;

public class Game {

	private String Team_Opponent;
	private int GameID,GoalHome,GoalGuest;
	private Date Date;
	
	public Game(int gameID,String teamOpponent,int goalHome,int goalGuest,Date date) {
		super();
		this.GameID = gameID;
		this.Team_Opponent = teamOpponent;
		this.GoalHome = goalHome;
		this.GoalGuest = goalGuest;
		this.Date = date;
	}

	public String getTeam_Opponent() {
		return Team_Opponent;
	}

	public int getGoalHome() {
		return GoalHome;
	}

	public int getGameID() {
		return GameID;
	}

	public int getGoalGuest() {
		return GoalGuest;
	}

	public Date getDate() {
		return Date;
	}
}
