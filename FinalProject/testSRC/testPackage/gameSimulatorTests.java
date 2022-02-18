package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import srcPackage.Coach;
import srcPackage.CoachDB;
import srcPackage.Controller;
import srcPackage.Driver;
import srcPackage.FakeDataBase;
import srcPackage.GameSimulator;
import srcPackage.InjuryTypes;
import srcPackage.Model;
import srcPackage.PositionTypes;
import srcPackage.Team;
import srcPackage.Player;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class gameSimulatorTests {
	
	public void clearConsole() {
		for(int clear = 0; clear < 100; clear++)
		  {
		     System.out.println("\b") ;
		  }
	}
	
	public boolean indenticalCheck(Team team) {
		
		ArrayList<Player> container = new ArrayList<Player>();
		int j;
		for(j = 0; j < team.getPlayers().size(); j++) {
			Player p = team.getPlayers().get(j);
			if(container.contains(p)) {
				return true;
			}
			container.add(p);
		}
		return false;
	}
	
	
	
	@Test
	public void testSingleGameTeamScoring() {
		int i;
		for(i = 0; i < 200; i++) {
			ArrayList<Player> db = new FakeDataBase().getDatabase();
			ArrayList<Coach> coachDb = new CoachDB().getCoaches();
			Team team1 = new Team();
			Team team2 = new Team();
			team1.autoGenerate(db, coachDb);
			team2.autoGenerate(db, coachDb);
			team1.autoGenerateName();
			team2.autoGenerateName();
			team1.setSingleGameScoring(team2);
			team2.setSingleGameScoring(team1);
			int team1Score = team1.getSingleGameScoring();
			int team2Score = team2.getSingleGameScoring();
			boolean notTooHigh = team1Score < 300;
			assertEquals(notTooHigh, true);
			notTooHigh = team2Score < 300;
			assertEquals(notTooHigh, true);
			boolean notTooLow = team1Score > 0;
			assertEquals(notTooLow, true);
			notTooLow = team2Score > 0;
			assertEquals(notTooLow, true);
		}
		clearConsole();
	}
	
	@Test
	public void testTeamExpectedPoints() {
		int i;
		for(i = 0; i < 200; i++) {
			ArrayList<Player> db = new FakeDataBase().getDatabase();
			ArrayList<Coach> coachDb = new CoachDB().getCoaches();
			Team team1 = new Team();
			
			team1.autoGenerate(db, coachDb);
			
			team1.autoGenerateName();
			
			team1.setAdjustedStats();
			
			double team1Score = team1.expectedPointsPerGame();
			
			boolean notTooHigh = team1Score < 300;
			assertEquals(notTooHigh, true);
			
			boolean notTooLow = team1Score > 0;
			assertEquals(notTooLow, true);
		}
		clearConsole();
	}
	
	@Test
	public void testSingleGamePlayerScoring() {
		
		int i;
		for(i = 0; i < 200; i++) {
			ArrayList<Player> db = new FakeDataBase().getDatabase();
			ArrayList<Coach> coachDb = new CoachDB().getCoaches();
			Team team1 = new Team();
			Team team2 = new Team();
			team1.autoGenerate(db, coachDb);
			team2.autoGenerate(db, coachDb);
			team1.autoGenerateName();
			team2.autoGenerateName();
			team1.setSingleGameScoring(team2);
			team2.setSingleGameScoring(team1);
			
			int team1PointsAddedUp = 0;
			int team2PointsAddedUp = 0;
			
			int j;
			for(j = 0; j < 10; j++) {
				int points1 = team1.getPlayers().get(j).getSingleGamePoints();
				int points2 = team2.getPlayers().get(j).getSingleGamePoints();
				team1PointsAddedUp += points1;
				team2PointsAddedUp += points2;
				boolean notTooHigh = points1 < 100;
				assertEquals(notTooHigh, true);
				notTooHigh = points2 < 100;
				assertEquals(notTooHigh, true);
				boolean notTooLow = points1 > -1;
				
				assertEquals(notTooLow, true);
				
				notTooLow = points2 > -1;
				assertEquals(notTooLow, true);
			}
			assertEquals(team1PointsAddedUp, team1.getSingleGameScoring());
			assertEquals(team2PointsAddedUp, team2.getSingleGameScoring());
		}
		clearConsole();
	}
	
	@Test
	public void testAdjustedScoring() {
		int i;
		for(i = 0; i < 100; i++) {
			ArrayList<Player> db = new FakeDataBase().getDatabase();
			ArrayList<Coach> coachDb = new CoachDB().getCoaches();
			Team team1 = new Team();
			
			team1.autoGenerate(db, coachDb);
			
			team1.autoGenerateName();
			
			team1.setAdjustedStats();
			
			
			double team1PointsAddedUp = 0;
			
			
			int j;
			for(j = 0; j < 10; j++) {
				double points1 = team1.getPlayers().get(j).getAdjustedPoints();
		
				team1PointsAddedUp += points1;
				
				boolean notTooHigh = points1 < 100;
				assertEquals(notTooHigh, true);
				
				boolean notTooLow = points1 > -1;
				
				assertEquals(notTooLow, true);

			}
			assertEquals(team1PointsAddedUp, team1.expectedPointsPerGame(), 1);
		}
		
	}
	
	@Test
	public void testSetAdjustedStats() {
		ArrayList<Player> db = new FakeDataBase().getDatabase();
		Optional<Player> randleContainer = db.stream().filter(p->p.getName().equals("Julius Randle")).findAny();
		Optional<Player> roseContainer = db.stream().filter(p->p.getName().equals("Derrick Rose")).findAny();
		Optional<Player> tatumContainer = db.stream().filter(p->p.getName().equals("Jayson Tatum")).findAny();
		Player JuliusRandle = randleContainer.get();
		Player JaysonTatum = tatumContainer.get();
		Player DerrickRose = roseContainer.get();
		JuliusRandle.setAdjustedStats(10);
		assertEquals(JuliusRandle.getAdjustedPoints(), 6.42, .1);
		JuliusRandle.setAdjustedStats(20);
		assertEquals(JuliusRandle.getAdjustedPoints(), 12.8, .1);
		JuliusRandle.setAdjustedStats(30);
		assertEquals(JuliusRandle.getAdjustedPoints(), 19.3, .1);
		assertThrows(IllegalArgumentException.class, () -> {JuliusRandle.setAdjustedStats(-1);});
		
		JaysonTatum.setAdjustedStats(10);
		assertEquals(JaysonTatum.getAdjustedPoints(), 7.39, .1);
		JaysonTatum.setAdjustedStats(20);
		assertEquals(JaysonTatum.getAdjustedPoints(), 14.78, .1);
		JaysonTatum.setAdjustedStats(30);
		assertEquals(JaysonTatum.getAdjustedPoints(), 22.17, .1);
		assertThrows(IllegalArgumentException.class, () -> {JaysonTatum.setAdjustedStats(-1);});
		
		
		DerrickRose.setAdjustedStats(10);
		assertEquals(DerrickRose.getAdjustedPoints(), 5.35, .1);
		DerrickRose.setAdjustedStats(20);
		assertEquals(DerrickRose.getAdjustedPoints(), 10.7, .1);
		DerrickRose.setAdjustedStats(30);
		assertEquals(DerrickRose.getAdjustedPoints(), 16.05, .1);
		assertThrows(IllegalArgumentException.class, () -> {DerrickRose.setAdjustedStats(-1);});
	}
	
	@Test public void testAutoGenerate() {
		int i;
		for(i = 0; i < 100; i++) {
			ArrayList<Player> db = new FakeDataBase().getDatabase();
			ArrayList<Coach> coachDb = new CoachDB().getCoaches();
			Team team1 = new Team();
			team1.autoGenerate(db, coachDb);
			assertEquals(10, team1.getPlayers().size());
			
			boolean IdenticalPlayersExist = indenticalCheck(team1);
			assertEquals(IdenticalPlayersExist, false);
			boolean coachExists = team1.getCoach() != null;
			assertEquals(coachExists, true);
		}
	}
	
	@Test
	public void testSortByOverall() {
		ArrayList<Player> db = new FakeDataBase().getDatabase();
		ArrayList<Coach> coachDb = new CoachDB().getCoaches();
		Team team = new Team();
		team.autoGenerate(db, coachDb);
		ArrayList<Player> sortedPlayers = team.sortByOverall(team.getPlayers());
		int i;
		for(i = 1; i < sortedPlayers.size(); i++) {
			Player p1 = sortedPlayers.get(i-1);
			Player p2 = sortedPlayers.get(i-1);
			boolean p1GreaterThanOrEqualp2 = p1.getOverall() >= p2.getOverall();
			assertEquals(p1GreaterThanOrEqualp2, true);
		}
	}
	
	@Test
	public void testCreateRotation() {
		int j;
		for(j = 0; j < 200; j++) {
			ArrayList<Player> db = new FakeDataBase().getDatabase();
			ArrayList<Coach> coachDb = new CoachDB().getCoaches();
			Team team = new Team();
			//autoGenerate calls the createRotation function which distributes minutes to players based on overall skill points
			team.autoGenerate(db, coachDb);
			ArrayList<Player> starters = team.getStarters();
			ArrayList<Player> backups = team.getBackups();
			ArrayList<Player> sortedStarters = team.sortByOverall(starters);
			ArrayList<Player> sortedBackups = team.sortByOverall(backups);
			Player BestPlayer = sortedStarters.get(0);
			Player SecondBestPlayer = sortedStarters.get(1);
			Player ThirdBestPlayer = sortedStarters.get(2);
			Player FourthBestPlayer = sortedStarters.get(3);
			Player FifthBestPlayer = sortedStarters.get(4);
			Player SixthBestPlayer = sortedBackups.get(0);
			Player SeventhBestPlayer = sortedBackups.get(1);
			Player EighthBestPlayer = sortedBackups.get(2);
			Player NinthBestPlayer = sortedBackups.get(3);
			Player WorstPlayer = sortedBackups.get(4);
			team.setAdjustedStats();
			
			assertEquals(BestPlayer.getAdjustedMinutes(), 37.0);
			assertEquals(SecondBestPlayer.getAdjustedMinutes(), 34.0);
			assertEquals(ThirdBestPlayer.getAdjustedMinutes(), 30.0);
			assertEquals(FourthBestPlayer.getAdjustedMinutes(), 28.0);
			assertEquals(FifthBestPlayer.getAdjustedMinutes(), 27.0);
			assertEquals(SixthBestPlayer.getAdjustedMinutes(), 21.0);
			assertEquals(SeventhBestPlayer.getAdjustedMinutes(), 20.0);
			assertEquals(EighthBestPlayer.getAdjustedMinutes(), 18.0);
			assertEquals(NinthBestPlayer.getAdjustedMinutes(), 14.0);
			assertEquals(WorstPlayer.getAdjustedMinutes(), 11.0);
			
			
			Player StartingPointGuard = team.getPointGuards().get(0);
			Player StartingShootingGuard = team.getShootingGuards().get(0);
			Player StartingSmallForward = team.getSmallForwards().get(0); 
			Player StartingPowerForward = team.getPowerForwards().get(0);
			Player StartingCenter = team.getCenters().get(0);
			Player BackupPointGuard = team.getPointGuards().get(1);
			Player BackupShootingGuard = team.getShootingGuards().get(1);
			Player BackupSmallForward = team.getSmallForwards().get(1);
			Player BackupPowerForward = team.getPowerForwards().get(1);
			Player BackupCenter = team.getCenters().get(1);
			
			boolean minutesAllocationCorrect = StartingPointGuard.getAdjustedMinutes() > BackupPointGuard.getAdjustedMinutes();
			assertEquals(minutesAllocationCorrect, true);
			minutesAllocationCorrect = StartingShootingGuard.getAdjustedMinutes() > BackupShootingGuard.getAdjustedMinutes();
			assertEquals(minutesAllocationCorrect, true);
			minutesAllocationCorrect = StartingSmallForward.getAdjustedMinutes() > BackupSmallForward.getAdjustedMinutes();
			assertEquals(minutesAllocationCorrect, true);
			minutesAllocationCorrect = StartingPowerForward.getAdjustedMinutes() > BackupPowerForward.getAdjustedMinutes();
			assertEquals(minutesAllocationCorrect, true);
			minutesAllocationCorrect = StartingCenter.getAdjustedMinutes() > BackupCenter.getAdjustedMinutes();
			assertEquals(minutesAllocationCorrect, true);
		}

	}
	
	@Test
	public void testWithinReasonRandom(){
		int i;
		for(i = 0; i < 200; i++) {
			Random rand = new Random();
			double num = (double)rand.nextInt(100) + 1;
			Player p = new Player();
			int newNum = p.withinReasonRandom(num);
			int difference = (int) Math.abs(newNum - num);
			boolean notTooHigh = difference < 20;
			boolean nonNeg = newNum > -1;
			
			assertEquals(notTooHigh, true);
			assertEquals(nonNeg, true);
		}
	}
	
	
	
}
