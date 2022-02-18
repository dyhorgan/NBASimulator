package srcPackage;

public class Coach {
	int offenseRating;
	int defenseRating;
	String name;
	boolean assigned;
	
	Coach(int offenseRating, int defenseRating, String name){
		this.offenseRating = offenseRating;
		this.defenseRating = defenseRating;
		this.name = name;
		this.assigned = false;
	}
	public int getOffenseRating() {
		return offenseRating;
	}
	public void setOffenseRating(int offenseRating) {
		this.offenseRating = offenseRating;
	}
	public int getDefenseRating() {
		return defenseRating;
	}
	public void setDefenseRating(int defenseRating) {
		this.defenseRating = defenseRating;
	}
	
	public void print() {
		System.out.print(this.name);
	}
	public void assign() {
		this.assigned = true;
	}
	public int getOffensiveBonus() {
		return this.offenseRating - 5;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAssigned() {
		return assigned;
	}
	public void setAssign(boolean bool) {
		this.assigned = bool;
	}
	
	
}
