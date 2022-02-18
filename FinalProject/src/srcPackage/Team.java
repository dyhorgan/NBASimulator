package srcPackage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Scanner;



public class Team {
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Player> pointGuards = new ArrayList<Player>();
	ArrayList<Player> shootingGuards = new ArrayList<Player>();
	ArrayList<Player> smallForwards = new ArrayList<Player>();
	ArrayList<Player> powerForwards = new ArrayList<Player>();
	ArrayList<Player> centers = new ArrayList<Player>();
	ArrayList<ArrayList<Player>> organizedPlayers = new ArrayList<ArrayList<Player>>();
	Coach coach;
	String name;
	String city;
	int chemistry;
	int singleGameScore;
	
	Team(String name, String city){
		this.name = name;
		this.city = city;
		this.organizedPlayers.add(this.pointGuards);
		this.organizedPlayers.add(this.shootingGuards);
		this.organizedPlayers.add(this.smallForwards);
		this.organizedPlayers.add(this.powerForwards);
		this.organizedPlayers.add(this.centers);
		this.chemistry = 0;
	}
	public Team(){
		this.organizedPlayers.add(this.pointGuards);
		this.organizedPlayers.add(this.shootingGuards);
		this.organizedPlayers.add(this.smallForwards);
		this.organizedPlayers.add(this.powerForwards);
		this.organizedPlayers.add(this.centers);
		this.chemistry = 0;
	}
	
	public ArrayList<Player> getPointGuards() {
		return pointGuards;
	}
	public ArrayList<Player> getShootingGuards() {
		return shootingGuards;
	}
	public ArrayList<Player> getSmallForwards() {
		return smallForwards;
	}
	public ArrayList<Player> getPowerForwards() {
		return powerForwards;
	}
	public ArrayList<Player> getCenters() {
		return centers;
	}
	public void printCoach() {
		System.out.print("Coach: ");
		this.coach.print();
		System.out.print("\n");
	}
	
	public void setChemistry() {
		int num = 0;
		for(Player p1 : this.players) {
			for(Player p2 : this.players) {
				if(p1.getChemistryBonusTeammates().contains(p2)) {
					num += 1;
				}
			}
		}
		int chemistryValue = (int)num/2;

		this.chemistry = chemistryValue;
		
	}
	
	public int getChemistry() {
		return this.chemistry;
	}
	
	public void printChemistry() {
		System.out.println("Team Chemistry Score is: "+ this.getChemistry());
		System.out.println("----------------------------------------------");
	}
	
	public void addPlayer(Player p){
		players.add(p);
		p.setAssigned(true);
		p.setTeam(this);
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
		coach.assign();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void toPrint() {
		System.out.println("\n\n----------------------------------------------");
		System.out.println("The " +this.city+" "+this.name+" :\n");
		int i = 0;
		for(Player player: this.getPlayers()) {
			player.print();
			if(i != (this.players.size() - 1)) {
				System.out.println("\n");
			}
			i += 1;
		}
		System.out.println("\n----------------------------------------------");
	}
	
	/*
	 * randomly determines whether an injury has occured for a team. The player injured and
	 * the type of injury are determined elsewhere.
	 */
	public boolean didInjury() {
		Random rand = new Random();
		int num = rand.nextInt(4);
		if(num == 1) {
			System.out.println(this.name + " - INJURY OCCURED");
			return true;
		}else {
			System.out.println(this.name + " - NO INJURIES");
			return false;
		}
	}
	
	/*
	 * When a player gets injured, I just redistribute his minutes to the whole rest of the 
	 * team rather than giving the minutes all to the backup. This is partly because it's a little simpler,
	 * but also because my version of teams doesn't include backups to the backups, who would then be
	 * getting the normal backup minutes.
	 */
	public void redistributeInjuryMinutes(Player player) {
		int minutes = (int)player.getAdjustedMinutes();

		int i = 0;
		while(minutes > 0) {
			
			Player p = this.players.get(i);
			if(p != player) {
				p.setSingleGameMinutes(p.getSingleGameMinutes() + 1);
				minutes -= 1;
			}
			if(i < (this.players.size() - 1)) {
				i += 1;
			}else {
				i = 0;
			}
		}
	}
	
	public void initializeSingleGameMinutes() {
		for(Player p : this.players) {
			p.setSingleGameMinutes(p.getAdjustedMinutes());
		}
	}
	
	/*
	 * This is generally how I deal with extra points coming from a coaching or chemistry
	 * bonus, just randomly allocate them to players
	 */
	public void reAdjustSingleGameStats(int overallAffect, int injuredIndex) {
		Random rand = new Random();
//			System.out.println("overall affect is: " + overallAffect);
			while(overallAffect != 0) {
				int randIndex = rand.nextInt(10);
				if(randIndex == injuredIndex) {
					randIndex = 0;
				}
				Player randomPlayer = this.players.get(randIndex);
				if(randomPlayer.getSingleGamePoints() == 0) {
					continue;
				}
				if(overallAffect > 0) {

					randomPlayer.setSingleGamePoints(randomPlayer.getSingleGamePoints() + 1);

					overallAffect -= 1;
				}else {

					randomPlayer.setSingleGamePoints(randomPlayer.getSingleGamePoints() - 1);

					overallAffect += 1;
				}
				
			}
	}
	/*
	 * In case you didn't read it elsewhere - adjusted stats = full season averages and singlegame stats are self explanatory
	 */
	public void reAdjustAdjustedStats(int overallAffect) {
		Random rand = new Random();

			while(overallAffect != 0) {
				
				int randIndex = rand.nextInt(10);
				
				Player randomPlayer = this.players.get(randIndex);
				
				if(overallAffect > 0) {
					
					randomPlayer.incrementAdjustedPoints();
					
					overallAffect -= 1;
				}else {
					
					randomPlayer.decrementAdjustedPoints();

					overallAffect += 1;
				}
				
			}
	}
	
	public int getSingleGameScoring() {
		return this.singleGameScore;
	}
	
	/*
	 * how a team scores on a particular night will depend on the defense of their opponent. Quality of coaching, chemistry,
	 * and random injury are also factors.
	 */
	public void setSingleGameScoring(Team opposing) {
		
		double opposingDRating = opposing.getTeamDefensiveRating();
		
		int averageDRating = 78;
		
		int defenseAffect = (int)(opposingDRating - averageDRating)/4;
		
		int coachAffect = this.coach.getOffenseRating() - 5;
		
		this.setAdjustedStats();
		
		boolean injury = didInjury();
		
		int injuredPlayerIndex = -1;
		int injuryTypeNum = 0;
		
		if(injury) {
			Random rand = new Random();
			injuredPlayerIndex = rand.nextInt(10);
			injuryTypeNum = rand.nextInt(5);
		}
		
		InjuryTypes injuryType = InjuryTypes.values()[injuryTypeNum];
		int contextAffects = this.chemistry + coachAffect - defenseAffect;
		
		int num = 0;
		int i = 0;
		Player injuredPlayer;
		this.initializeSingleGameMinutes();
		if(injuredPlayerIndex > -1) {
			injuredPlayer = this.players.get(injuredPlayerIndex);
			injuredPlayer.setInjured();
			System.out.println(injuredPlayer.getName() + " is injured - "+ injuryType.toString());
			this.redistributeInjuryMinutes(injuredPlayer);
		}
		
		for(Player p : this.players) {
			if(injuredPlayerIndex != i) {
				p.setSingleGameStats(p.getSingleGameMinutes());
				num += p.getSingleGamePoints();
			}
			i += 1;
		}

		this.reAdjustSingleGameStats(contextAffects, injuredPlayerIndex);
		
		 this.singleGameScore = num + contextAffects;
	}
	
	public void incrementSingleGameScoring() {
		this.singleGameScore += 1;
		Random rand = new Random();
		int playerIndex = rand.nextInt(10);
		Player randomPlayer = this.players.get(playerIndex);
		randomPlayer.setSingleGamePoints(randomPlayer.getSingleGamePoints() + 1);
	}
	
	public void printSingleGameScoring() {
		System.out.print("\n");
		System.out.println(this.name + " scored: " + this.singleGameScore + " points");
	}
	
	public double expectedPointsPerGame() {
		this.setAdjustedStats();
		double num = this.players.stream().map(p->p.getAdjustedPoints()).reduce(0.0, (points1,points2)->{return points1 + points2;});
		
		return num;
	}
	/*
	 * The amount of points the team is expected to score in a game, important for season simulator
	 */
	public void printExpectedPointsPerGame() {
		System.out.print("\n" + this.name + " expected points per game: ");
		double num = this.expectedPointsPerGame();
		System.out.print(df2.format(num) + "\n");
	}
	
	/*
	 * The amount of points the team's opponents are expected to score in a game on average, important for season simulator
	 */
	public double expectedPointsAllowed() {
		double teamDefensiveRating = this.getTeamDefensiveRating();
		int averageDefensiveRating = 79;
		int averagePointsAllowed = 111;

		double expectedPoints = ((averageDefensiveRating - teamDefensiveRating)/3)+averagePointsAllowed;
		
		int coachAffect = this.coach.getDefenseRating() - 5;
		expectedPoints -= coachAffect;
		return expectedPoints;
	}
	
	public double getTeamDefensiveRating() {
		int num = this.players.stream().map(p->p.getDefensiveRating()).reduce(0, (rating1,rating2)->{return rating1 + rating2;});
		double teamDefensiveRating = num/10;
		int coachAffect = this.coach.getDefenseRating() - 5;
		teamDefensiveRating += coachAffect;
		return teamDefensiveRating;
	}
	
	public void printTeamDefensiveRating() {
		double teamDefensiveRating = this.getTeamDefensiveRating();
		System.out.println("Team Average Defensive Rating is: " + teamDefensiveRating);
	}
	
	public void printExpectedPointsAllowed() {
		System.out.print("\n"+this.name + " expected points allowed per game:");
		System.out.print(df2.format(this.expectedPointsAllowed()) + "\n");
	}
	
	public double expectedPointDifferential() {
		double differential = this.expectedPointsPerGame() - this.expectedPointsAllowed();
		return differential;
	}
	
	
	public int[] expectedRecord() {
		int record[] = new int[2];
		double winPercentage = this.expectedWinPercentage();
		float rawWins = (float) (winPercentage * 82);
		int expectedWins = Math.round(rawWins);
		int expectedLosses = 82 - expectedWins;
		if(expectedLosses < 0) {
			expectedLosses = 1;
			expectedWins = 81;
		}
		if(expectedWins < 0) {
			expectedWins = 1;
			expectedLosses = 81;
		}
		record[0] = expectedWins;
		record[1] = expectedLosses;
		return record;
	}
	
	public void printExpectedRecord() {
		int record[] = this.expectedRecord();
		System.out.println("\nExpected Record: "+ record[0]+ " wins - "+record[1]+" losses");
		System.out.print("\n");
	}
	
	/*
	 * finds expected win percentage based on point differential (expected points the team scores vs. points our opponents score).
	 * The specific numbers are just taken from something I read online about an NBA teams' expected record is based on their
	 * point differential.
	 */
	public double expectedWinPercentage() {
		double differential = this.expectedPointDifferential();
		double winPercentage = ((differential*2.7)+41)/82;
		return winPercentage;
	}
	
	public void printAdjustedStats() {
		
		if(this.pointGuards.size() == 0) {
			this.createRotation();
		}else {
			this.sortAllPositions();
		}
		System.out.println("\n");
		System.out.println("-----------------------------------------------");
		System.out.println(this.name + " expected stats:");
		this.setAdjustedStats();
		for(ArrayList<Player> positionArray : this.organizedPlayers) {
				Player starter = positionArray.get(0);
				Player backup = positionArray.get(1);

				starter.printAdjustedStats();
				backup.printAdjustedStats();
		}
		System.out.println("-----------------------------------------------");
		
	}
	
	public void printSingleGameStats() {
		System.out.print("\n");
		System.out.println("-----------------------------------------------");
		System.out.println(this.name + " Single Game Stats");
		int i;
		for(i = 0; i < this.players.size(); i++) {
			Player player = this.players.get(i);
			player.printSingleGameStats();
		}
		System.out.println("-----------------------------------------------");
		System.out.print("\n");
	}
	
	public void setAdjustedStats() {
		
		ArrayList<Player> starters = this.getStarters();
		ArrayList<Player> backups = this.getBackups();
		
		this.sortByOverall(starters);
		
		this.sortByOverall(backups);
		
		starters.get(0).setAdjustedStats(37);
	    starters.get(1).setAdjustedStats(34);
	    starters.get(2).setAdjustedStats(30);
	    starters.get(3).setAdjustedStats(28);
	    starters.get(4).setAdjustedStats(27);
	    
	    backups.get(0).setAdjustedStats(21);
	    backups.get(1).setAdjustedStats(20);
	    backups.get(2).setAdjustedStats(18);
	    backups.get(3).setAdjustedStats(14);
	    backups.get(4).setAdjustedStats(11);
	    
	    this.reAdjustAdjustedStats(this.coach.getOffensiveBonus());
	    this.reAdjustAdjustedStats(this.chemistry);
	    
	}
	
	public ArrayList<Player> getStarters() {
		ArrayList<Player> Starters = new ArrayList<Player>();
		
		for(ArrayList<Player> positionArray : this.organizedPlayers) {
			Starters.add(positionArray.get(0));
		}
		return Starters;
	}
	public ArrayList<Player> getBackups() {
		ArrayList<Player> Backups = new ArrayList<Player>();
		
		for(ArrayList<Player> positionArray : this.organizedPlayers) {
			Backups.add(positionArray.get(1));
		}
		return Backups;
	}
	
	public ArrayList<Player> sortByOverall(ArrayList<Player> positionArray) {

		positionArray.sort((p,p2)-> {return p2.getOverall() - p.getOverall();});
		return positionArray;
	}
	
	public void sortAllPositions() {
		this.sortByOverall(this.pointGuards);
		this.sortByOverall(this.shootingGuards);
		this.sortByOverall(this.smallForwards);
		this.sortByOverall(this.powerForwards);
		this.sortByOverall(this.centers);
	}
	
	/*
	 * basically organizes players into positions and calls sortAllPositions to determin starters vs backups
	 */
	public void createRotation() {
		for(Player p : this.players) {
			if(p.getPosition() == PositionTypes.PG) {
				this.pointGuards.add(p);
			}else if(p.getPosition() == PositionTypes.SG) {
				this.shootingGuards.add(p);
			}else if(p.getPosition() == PositionTypes.SF) {
				this.smallForwards.add(p);
			}else if(p.getPosition() == PositionTypes.PF) {
				this.powerForwards.add(p);
			}else {
				this.centers.add(p);
			}
		}
		this.sortAllPositions();
	}
	
	public void printDepthChart() {
		String customSpace;
		if(this.pointGuards.size() == 0) {
			this.createRotation();
		}else {
			this.sortAllPositions();
		}
		System.out.println("----------------------------------------------");
		System.out.println("The " + this.name + " Depth Chart:");
		System.out.print("Point Guard:\t");
		for(Player p : this.pointGuards) {
			if(p.getName().length() < 15) {
				customSpace = "\t\t";
			}else {
				customSpace = "\t";
			}
			System.out.print(p.getName() + customSpace);
		}
		System.out.print("\n");
		System.out.print("Shooting Guard:\t");
		for(Player p : this.shootingGuards) {
			if(p.getName().length() < 15) {
				customSpace = "\t\t";
			}else {
				customSpace = "\t";
			}
			System.out.print(p.getName() + customSpace);
		}
		System.out.print("\n");
		System.out.print("Small Forward:\t");
		for(Player p : this.smallForwards) {
			if(p.getName().length() < 15) {
				customSpace = "\t\t";
			}else {
				customSpace = "\t";
			}
			System.out.print(p.getName() + customSpace);
		}
		System.out.print("\n");
		System.out.print("Power Forward:\t");
		for(Player p : this.powerForwards) {
			if(p.getName().length() < 15) {
				customSpace = "\t\t";
			}else {
				customSpace = "\t";
			}
			System.out.print(p.getName() + customSpace);
		}
		System.out.print("\n");
		System.out.print("Centers:\t");
		
		
		for(Player p : this.centers) {
			if(p.getName().length() < 15) {
				customSpace = "\t\t";
			}else {
				customSpace = "\t";
			}
			System.out.print(p.getName() + customSpace);
		}
		System.out.println("\n----------------------------------------------");
		
	}
	
	/*
	 * This is where the roster is built if a user just doesn't feel like doing it themselves. 
	 */
	public void autoGenerate(ArrayList<Player> db, ArrayList<Coach> coachDb) {
		
		ArrayList<ArrayList<Player>> playerOrganization = new ArrayList<ArrayList<Player>>();
		
		ArrayList<Player> pointGuards = db.stream().filter(p -> p.isAssigned() == false && p.getPosition() == PositionTypes.PG).collect(Collectors.toCollection(ArrayList::new));
		playerOrganization.add(pointGuards);
		ArrayList<Player> shootingGuards = db.stream().filter(p -> p.isAssigned() == false && p.getPosition() == PositionTypes.SG).collect(Collectors.toCollection(ArrayList::new));
		playerOrganization.add(shootingGuards);
		ArrayList<Player> smallForwards = db.stream().filter(p -> p.isAssigned() == false && p.getPosition() == PositionTypes.SF).collect(Collectors.toCollection(ArrayList::new));
		playerOrganization.add(smallForwards);
		ArrayList<Player> powerForwards = db.stream().filter(p -> p.isAssigned() == false && p.getPosition() == PositionTypes.PF).collect(Collectors.toCollection(ArrayList::new));
		playerOrganization.add(powerForwards);
		ArrayList<Player> centers = db.stream().filter(p -> p.isAssigned() == false && p.getPosition() == PositionTypes.C).collect(Collectors.toCollection(ArrayList::new));
		playerOrganization.add(centers);
		Random rand = new Random();
		for(ArrayList<Player> positionArray : playerOrganization) {
			boolean positionFull = false;
			int positionTotal = 0;
			while(!positionFull) {
				
				int arrayLength = positionArray.size();
				int randInt = rand.nextInt(arrayLength);
				Player newPlayer = positionArray.get(randInt);
				positionArray.remove(randInt);
				this.addPlayer(newPlayer);
				positionTotal += 1;
				if(positionTotal == 2) {
					positionFull = true;
				}
			}
		}
		ArrayList<Coach> unassignedCoaches = coachDb.stream().filter(c->c.assigned == false).collect(Collectors.toCollection(ArrayList::new));
		int index = rand.nextInt(unassignedCoaches.size());
		this.coach = unassignedCoaches.get(index);
		this.setChemistry();
		this.createRotation();
	}
	
	public void autoGenerateName() {
		this.city = "Location";
		this.name = "Basketball Team";
	}
	public void clearAssigned() {
		for(Player p : this.players) {
			p.setAssigned(false);
		}
	}
}
