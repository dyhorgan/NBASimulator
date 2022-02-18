package srcPackage;
import java.text.DecimalFormat;
import java.util.Random;

public class GameSimulator {
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	Team team1;
	
	Team team2;
	
	int team1SingleScore;
	int team2SingleScore;
	
	GameSimulator(Team team1, Team team2){
		this.team1 = team1;
		this.team2 = team2;
	}
	
	GameSimulator(Team team1){
		this.team1 = team1;
	}
	
	GameSimulator(){
		
	}
	
	public void simulateBoxScore(){
		System.out.println("Box Score: ");
	}
	public Team whoWon() {
		if(team1SingleScore > team2SingleScore) {
			return this.team1;
		}else if(team1SingleScore < team2SingleScore){
			return this.team2;
		}else {
			return null;
		}
	}
	public void winMessage() {
		Team winner = this.whoWon();
		if(winner != null) {
			int deficit = Math.abs(this.team1SingleScore - this.team2SingleScore);
			System.out.println("The " + winner.getName() + " won by " + deficit + " points");
		}
	}
	
	public int[] breakTie(Team team1, Team team2) {
		Random rand = new Random();
		int coinFlip = rand.nextInt(2);
		Team winningTeam;
		if(coinFlip == 0) {
			winningTeam = team1;
		}else {
			winningTeam = team2;
		}
		winningTeam.incrementSingleGameScoring();
		int[] scoreArray = {team1.getSingleGameScoring(),team2.getSingleGameScoring()};
		return scoreArray;
	}
	
	
	public void printOutcomeExpectedDifference() {
		double differenceNum1 = Math.abs(this.team1.expectedPointsPerGame() - this.team1SingleScore);
		String difference1 = df2.format(differenceNum1);
		
		double differenceNum2 = Math.abs(this.team2.expectedPointsPerGame() - this.team2SingleScore);
		String difference2 = df2.format(differenceNum2);
		
		if(this.team1SingleScore > this.team1.expectedPointsPerGame()) {
			System.out.println("The " + team1.getName() + " scored " + difference1 + " points more than expected");
		}else {
			System.out.println("The " + team1.getName() + " scored " + difference1 + " points less than expected");
		}
		
		if(this.team2SingleScore > this.team2.expectedPointsPerGame()) {
			System.out.println("The " + team2.getName() + " scored " + difference2 + " points more than expected");
		}else {
			System.out.println("The " + team2.getName() + " scored " + difference2 + " points less than expected");
		}
	}
	
	public void simulateGame() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.team1.printDepthChart();
		this.team1.printCoach();
		this.team1.printChemistry();
		System.out.print("\n");
		this.team2.printDepthChart();
		this.team2.printCoach();
		this.team2.printChemistry();
	
		
		System.out.println("\nSimulating Game Between " + this.team1.getName() + " and " + this.team2.getName() + "...\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.team1.setSingleGameScoring(team2);
		this.team2.setSingleGameScoring(team1);
		int score = this.team1.getSingleGameScoring();
		int score2 = this.team2.getSingleGameScoring();
		if(score == score2) {
			int[] scoreArray = breakTie(team1, team2);
			score = scoreArray[0];
			score2 = scoreArray[1];
		}
		team1SingleScore = score;
		team2SingleScore = score2;
		this.team1.printSingleGameScoring();
		this.team2.printSingleGameScoring();
		this.team1.printSingleGameStats();
		this.team2.printSingleGameStats();
		this.winMessage();
		this.printOutcomeExpectedDifference();
		
	}
	
	public void simulateSeason() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.team1.printDepthChart();
		this.team1.printCoach();
		this.team1.printChemistry();
		System.out.println("Simulating Season For " + this.team1.getName() + "...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		team1.printAdjustedStats();
		team1.printExpectedPointsPerGame();
		team1.printExpectedPointsAllowed();
		team1.printTeamDefensiveRating();
		team1.printExpectedRecord();
	}
}
