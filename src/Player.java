
public class Player {
	public enum posstion {GK, CB, RB, LB, MC, DM, LW, RW, ST};
	public int PlayerID;
	private String Name,Position;
	private float Age;
	


	public Player(int playerID,String name, String position, float age) {
		super();
		PlayerID = playerID;
		Name = name;
		Position = position;
		Age = age;
	}

	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}
	
	public void setName(String name) {
		Name = name;
	}


	public void setPosition(String position) {
		Position = position;
	}


	public void setAge(float age) {
		Age = age;
	}


	public String getName() {
		return Name;
	}

	public String getPosition() {
		return Position;
	}

	public float getAge() {
		return Age;
	}
	
	
	
}
