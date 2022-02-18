package srcPackage;
import java.util.ArrayList;

public class Model {
	
	ArrayList<Player> db;
	ArrayList<Coach> coachDb;
	Team team;
	Team team2;
	GameSimulator gs;
	
	Model(ArrayList<Player> db, ArrayList<Coach> coachDb,Team team, Team team2, GameSimulator gs){
		this.db = db;
		this.coachDb = coachDb;
		this.team = team;
		this.team2 = team2;
		this.gs = gs;
	}

	public ArrayList<Player> getDb() {
		return db;
	}

	public void setDb(ArrayList<Player> db) {
		this.db = db;
	}

	public ArrayList<Coach> getCoachDb() {
		return coachDb;
	}

	public void setCoachDb(ArrayList<Coach> coachDb) {
		this.coachDb = coachDb;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public GameSimulator getGs() {
		return gs;
	}

	public void setGs(GameSimulator gs) {
		this.gs = gs;
	}
	public void clearPlayerAssignments() {
		for(Player p : this.db) {
			p.setAssigned(false);
		}
	}
	public void clearCoachAssignments() {
		for(Coach c : this.coachDb) {
			c.setAssign(false);
		}
	}
}
