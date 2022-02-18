package srcPackage;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	double minutes;
	double adjustedMinutes;
	int singleGameMinutes;
	boolean assigned;
	double points;
	int singleGamePoints;
	double adjustedPoints;
	double assists;
	int singleGameAssists;
	double adjustedAssists;
	int singleGameSteals;
	double steals;
	double adjustedSteals;
	double blocks;
	int singleGameBlocks;
	double adjustedBlocks;
	double rebounds;
	int singleGameRebounds;
	double adjustedRebounds;
	int defensiveRating;
	int overall;
	Team team;
	PositionTypes position;
	String name;
	boolean injured;
	
	ArrayList<Player> ChemistryBonusTeammates = new ArrayList<Player>();
	
	public Player(double minutes, double points, double assists, double steals, double blocks, double rebounds, int defensiveRating, int overall, Team team,
			PositionTypes position, String name, ArrayList<Player> ChemistryTeammates) {
		
		this.minutes = minutes;
		this.points = points;
		this.assists = assists;
		this.steals = steals;
		this.blocks = blocks;
		this.rebounds = rebounds;
		this.overall = overall;
		this.team = team;
		this.position = position;
		this.name = name;
		this.assigned = false;
		this.defensiveRating = defensiveRating;
		int i;
		for(i = 0; i < ChemistryTeammates.size(); i++) {
			Player player = ChemistryTeammates.get(i);
			this.ChemistryBonusTeammates.add(player);
		}
		this.injured = false;
		this.singleGamePoints = 0;
		this.singleGameSteals = 0;
		this.singleGameRebounds = 0;
		this.singleGameAssists = 0;
		this.singleGameBlocks = 0;
	}
	public Player(){
		this.injured = false;
		this.assigned = false;
		this.singleGamePoints = 0;
		this.singleGameSteals = 0;
		this.singleGameRebounds = 0;
		this.singleGameAssists = 0;
		this.singleGameBlocks = 0;
	}
	
	public void incrementAdjustedPoints() {
		this.adjustedPoints += 1;
	}
	public void decrementAdjustedPoints() {
		this.adjustedPoints -= 1;
	}
	
	public ArrayList<Player> getChemistryBonusTeammates(){
		return this.ChemistryBonusTeammates;
	}
	
	/*
	 * Teams get chemistry bonus points if specific players are paired with other specific players. This function
	 * adds players to an array of potential teammates for "this" player that would result in a chemistry bonus for
	 * the team
	 */
	public void addChemistryTeammate(Player player, boolean AddToParamPlayer) {
		this.ChemistryBonusTeammates.add(player);
		if(AddToParamPlayer) {
			player.addChemistryTeammate(this, false);
		}
	}
	
	/*
	 * This is the function that will spit out a number that is somewhat close to
	 * but off from a player's average. It is important for the single game simulator.
	 */
	public int withinReasonRandom(double num) {
		
		if(this.injured == true) {
			return 0;
		}
		int intNum;
		if(num > 1) {
			intNum = (int)num;
		}else {
			intNum = 1;
		}
		
		Random rand = new Random();
		int coinFlip = rand.nextInt(2);
		
		//If coin flip is even, number will be higher
		//than expected. If flip is odd, the opposite.
		int stopIncrementing = 15;
		int stopDecrementing = 15;
		int range = 15;
		if(coinFlip % 2 == 0) {
			while(stopIncrementing > 3 && range > 0 && intNum > 0) {
				stopIncrementing = rand.nextInt(range);
				intNum +=1;
				range -= 1;
			}
		}else {
			while(stopDecrementing > 3 && range > 0 && intNum > 0) {
				stopDecrementing = rand.nextInt(range);
				intNum -= 1;
				range -= 1;
			}
		}
		if(intNum < 0) {
			return 0;
		}
		return intNum;
	}
	
	
	public int getSingleGameMinutes() {
		return singleGameMinutes;
	}


	public void setSingleGameMinutes(double singleGameMinutes) {
		this.singleGameMinutes = (int) singleGameMinutes;
	}


	public int getSingleGamePoints() {
		return singleGamePoints;
	}
	
	public void setInjured() {
		this.singleGameAssists = 0;
		this.singleGameBlocks = 0;
		this.singleGameMinutes = 0;
		this.singleGameRebounds = 0;
		this.singleGameSteals = 0;
		this.singleGamePoints = 0;
		this.injured = true;
	}


	private void setSingleGamePoints() {
		double expectedPoints = this.getAdjustedPoints();
		int actualPoints = this.withinReasonRandom(expectedPoints);
		if(actualPoints < 0) {
			actualPoints = 0;
		}
		this.singleGamePoints = actualPoints;
	}
	public void setSingleGamePoints(int points) {
		this.singleGamePoints = points;
	}

	public int getSingleGameAssists() {
		return singleGameAssists;
	}


	private void setSingleGameAssists() {
		double expectedAssists = this.getAdjustedAssists();
		int actualAssists = this.withinReasonRandom(expectedAssists);
		if(actualAssists < 0) {
			actualAssists = 0;
		}
		this.singleGameAssists = actualAssists;
	}


	public int getSingleGameSteals() {
		return singleGameSteals;
	}


	private void setSingleGameSteals() {
		double expectedSteals = this.getAdjustedSteals();
		int actualSteals = this.withinReasonRandom(expectedSteals);
		if(actualSteals < 0) {
			actualSteals = 0;
		}
		this.singleGameSteals = actualSteals;
	}


	public int getSingleGameBlocks() {
		return singleGameBlocks;
	}


	private void setSingleGameBlocks() {
		double expectedBlocks = this.getAdjustedBlocks();
		int actualBlocks = this.withinReasonRandom(expectedBlocks);
		if(actualBlocks < 0) {
			actualBlocks = 0;
		}
		this.singleGameBlocks = actualBlocks;
	}


	public double getSingleGameRebounds() {
		return singleGameRebounds;
	}


	private void setSingleGameRebounds() {
		double expectedRebounds = this.getAdjustedRebounds();
		int actualRebounds = this.withinReasonRandom(expectedRebounds);
		if(actualRebounds < 0) {
			actualRebounds = 0;
		}
		this.singleGameRebounds = actualRebounds;
	}

	/*
	 * Adjusted Stats - for the season simulator, and also used as a foundation for getting SingleGame stats
	 */
	public void setAdjustedStats(double minutes){
		if(minutes < 0) {
			throw new IllegalArgumentException("Error: A player can't play negative minutes.");
		}
		this.adjustedMinutes = minutes;
		this.adjustedPoints = Math.round((minutes * this.getPointsPerMinute()) * 100.0)/100.0;
		this.adjustedAssists = Math.round((minutes * this.getAssistsPerMinute()) * 100.0)/100.0;
		this.adjustedSteals = Math.round((minutes * this.getStealsPerMinute()) * 100.0)/100.0;
		this.adjustedBlocks = Math.round((minutes * this.getBlocksPerMinute()) * 100.0)/100.0;
		this.adjustedRebounds = Math.round((minutes * this.getReboundsPerMinute()) * 100.0)/100.0;
	}
	
	public void setSingleGameStats(double minutes) {
		this.setAdjustedStats(minutes);
		this.setSingleGameBlocks();
		this.setSingleGameSteals();
		this.setSingleGameAssists();
		this.setSingleGamePoints();
		this.setSingleGameRebounds();
		this.setSingleGameMinutes(minutes);
		
	}
	public void printAdjustedStats() {
		String customName;
		if(this.name.length() > 15) {
			customName = this.name.substring(0,12) + "...";
		}else {
			customName = this.name;
		}
		System.out.println(customName + "\t MPG: " + this.adjustedMinutes + 
				"\t PTS: " + this.adjustedPoints + "\t AS: " + this.adjustedAssists + "\t STL: " + this.adjustedSteals + 
				"\t BLK: " + this.adjustedBlocks + "\t REB: " + this.adjustedRebounds);
	}
	
	public void printSingleGameStats() {
		String customName;
		if(this.name.length() > 15) {
			customName = this.name.substring(0,12) + "...";
		}else {
			customName = this.name;
		}
		System.out.println(customName + "\t MIN: " + this.singleGameMinutes + 
				" \t PTS: " + this.singleGamePoints + " \t AS: " + this.singleGameAssists + "\t STL: " + this.singleGameSteals + 
				"\t BLK: " + this.singleGameBlocks + "\t REB: " + this.singleGameRebounds);
	}
	
	public void print() {
		System.out.print(this.toString() + " ");
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.position;
	}

	public double getMinutes() {
		return minutes;
	}
	public double getAdjustedMinutes() {
		return adjustedMinutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public double getPoints() {
		return points;
	}
	public double getAdjustedPoints() {
		return this.adjustedPoints;
	}
	
	public double getPointsPerMinute() {
		return this.points/this.minutes;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public double getAssists() {
		return assists;
	}
	
	public double getAdjustedAssists(){
		return this.adjustedAssists;
	};
	
	public double getAssistsPerMinute() {
		return this.assists/this.minutes;
	}

	public void setAssists(double assists) {
		this.assists = assists;
	}

	public double getSteals() {
		return steals;
	}
	public double getAdjustedSteals() {
		return this.adjustedSteals;
	}
	public double getStealsPerMinute() {
		return this.steals/this.minutes;
	}

	public void setSteals(double steals) {
		this.steals = steals;
	}
	public double getAdjustedBlocks() {
		return this.adjustedBlocks;
	}
	public double getBlocks() {
		return blocks;
	}
	
	public double getBlocksPerMinute() {
		return this.blocks/this.minutes;
	}

	public void setBlocks(double blocks) {
		this.blocks = blocks;
	}

	public double getRebounds() {
		return rebounds;
	}
	public double getAdjustedRebounds() {
		return this.adjustedRebounds;
	}
	public double getReboundsPerMinute() {
		return this.rebounds/this.minutes;
	}

	public void setRebounds(double rebounds) {
		this.rebounds = rebounds;
	}
	public int getDefensiveRating() {
		return this.defensiveRating;
	}
	public void setDefensiveRating(int defensiveRating) {
		this.defensiveRating = defensiveRating;
	}
	public int getOverall() {
		return overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public PositionTypes getPosition() {
		return position;
	}

	public void setPosition(PositionTypes position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}