package srcPackage;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

	Model model;
	
	Controller(Model model){
		this.model = model;
	}
	
	/*
	 * selectTeam is used when a user wants to manually build a roster
	 */
    public void selectTeam(Team team, ArrayList<Player>db, ArrayList<Coach> coachDb, Scanner scan) {
    	int i;
		int lineNum = 0;
		for(i = 0; i < db.size(); i++) {
			Player p = db.get(i);
			if(i != 0) {
				if(!p.getPosition().equals(db.get(i - 1).getPosition())) {
					lineNum = 0;
					System.out.println("\n\n");
					boolean positionFull = false;
					int positionTotal = 0;
					System.out.println("Pick TWO of the above " + db.get(i - 1).getPosition() + "'s\n");
					System.out.println("Input one number at a time");
					while(!positionFull) {
						String stringNum = scan.nextLine();
						if(stringNum.length() == 0) {
							System.out.println("type a number");
							continue;
						}
						int num = Integer.parseInt(stringNum);
						if(num > db.size()) {
							System.out.println("invalid number, try again");
							continue;
						}
						Player player = db.get(num - 1);
						if(player.isAssigned() == true) {
							System.out.println("You can't pick the same player twice");
							continue;
						}
						if(player.getPosition() != db.get(i - 1).getPosition()) {
							System.out.println("invalid number, try again");
							continue;
						}
						team.addPlayer(player);
						System.out.println("You selected " + player.getName());
						positionTotal += 1;
						if(positionTotal == 2) {
							positionFull = true;
						}
					}
					System.out.println("");
				}else if(lineNum == 2) {
					lineNum = 0;
					System.out.println("\n");
				}else {
					lineNum += 1;
				}
			}
			
			System.out.print("[#"+ (i + 1) + " ");
			p.print();
			System.out.print("]");
		}
		
		lineNum = 0;
		System.out.println("\n\n");
		boolean positionFull = false;
		int positionTotal = 0;
		System.out.println("Pick TWO of the above " + db.get(i - 1).getPosition() + "'s\n");
		System.out.println("Input one number at a time\n");
		while(!positionFull) {
			String stringNum = scan.nextLine();
			if(stringNum.length() == 0) {
				System.out.println("type a number");
				continue;
			}
			int num = Integer.parseInt(stringNum);
			if(num > db.size()) {
				System.out.println("invalid number, try again");
				continue;
			}
			Player player = db.get(num - 1);
			if(player.isAssigned() == true) {
				System.out.println("You can't pick the same player twice");
				continue;
			}
			if(player.getPosition() != db.get(i - 1).getPosition()) {
				System.out.println("invalid number, try again");
				continue;
			}
			team.addPlayer(player);
			System.out.println("You selected " + player.getName());
			positionTotal += 1;
			if(positionTotal == 2) {
				positionFull = true;
			}
		}
		boolean coachSelected = false;
		String coachID;
		ArrayList<Coach> filteredCoachDb = coachDb.stream().filter((c)->c.isAssigned()==false).collect(Collectors.toCollection(ArrayList::new));
		while(!coachSelected) {
			System.out.println("\nWho would you like to coach your team?");
			int j;
			for(j = 0;j<filteredCoachDb.size();j++) {
				System.out.print((j+1)+": ");
				filteredCoachDb.get(j).print();
				System.out.print("\n");
			}
			coachID = scan.nextLine();
			int response = Integer.valueOf(coachID);
			if(response > (filteredCoachDb.size()-1) || response < 0) {
				System.out.println("input a coach's number");
				continue;
			}
			team.setCoach(filteredCoachDb.get(response - 1));
			coachSelected = true;
			team.getCoach().assign();
		}
		System.out.println("You selected: "+ team.getCoach().getName());
		
		
		team.setChemistry();
		team.createRotation();
    }
	
    /*
	 * This basically runs the whole program. Loops both the season simulator and single game simulator
	 */
	public void go(){
		int done = 0;
 		while(done == 0) {
 			this.model.clearPlayerAssignments();
 			model.getTeam().clearAssigned();
 			model.getTeam2().clearAssigned();
 			this.model.clearCoachAssignments();
 			
 			String simulationType = null;
 			
 			Scanner scan = new Scanner(System.in);
 			boolean modePicked = false;
 			while(!modePicked) {
 				System.out.println("Welcome to my Basketball Simulator!");
 	 			
 	 			System.out.println("Would you like to 1. simulate a game or 2. simulate a season (pick a number)?");
 	 			
 	 			simulationType = scan.nextLine();
 	 			
 	 			if(simulationType.equals("2")) {
 	 				System.out.println("Season Simulator");
 	 				modePicked = true;
 	 			}else if(simulationType.equals("1")){
 	 				System.out.println("Game Simulator");
 	 				modePicked = true;
 	 			}else {
 	 				System.out.println("input a valid number");
 	 			}
 			}
 			String city = null;
 			boolean citySelected = false;
 			while(!citySelected) {
 				System.out.println("Where is your team based (ex. Los Angeles)?");
 	 			
 	 			city = scan.nextLine();
 	 			if(city.length() < 1) {
 	 				System.out.println("Enter the name of a location");
 	 			}else {
 	 				citySelected = true;
 	 			}
 			}
 			String name = null;
 			boolean nameSelected = false;
 			while(!nameSelected) {
 				System.out.println("What is your team name (ex. Lakers)?");
 	 			
 	 			name = scan.nextLine();
 	 			if(name.length() < 1) {
 	 				System.out.println("Enter a team name");
 	 			}else {
 	 				nameSelected = true;
 	 			}
 			}
 			
 			this.model.setTeam(new Team(name, city));
 			
 			System.out.println("Build a 10 man roster\n");
 			boolean genResponse = false;
 			while(!genResponse) {
 				System.out.println("Would you like to auto-generate the team (y/n)?");
 				String answer = scan.nextLine();
 				
 				if(answer.equals("y")||answer.equals("Y")) {
 					this.model.getTeam().autoGenerate(this.model.getDb(), this.model.getCoachDb());
 					genResponse = true;
 				}else if(answer.equals("n")||answer.equals("N")){
 					System.out.println("Type a player's number to choose them for your roster: \n");
 					selectTeam(this.model.getTeam(), this.model.getDb(), this.model.getCoachDb(), scan);
 					genResponse = true;
 				}else {
 					System.out.println("type a valid response (y or n)");
 					continue;
 				}
 			}
 			
 			
 			this.model.getTeam().toPrint();

 			if(simulationType.equals("2")) {
 				this.model.setGs(new GameSimulator(this.model.getTeam()));
 				this.model.getGs().simulateSeason();
 			}else {
 				String opposingCity = null;
 				boolean opposingCitySelected = false;
 				
 				while(!opposingCitySelected) {
 					System.out.println("Where is the opposing team based?");
 					opposingCity = scan.nextLine();
 					if(opposingCity.length() < 1) {
 	 	 				System.out.println("Enter a team name");
 	 	 			}else {
 	 	 				opposingCitySelected = true;
 	 	 			}
 				}
 				
 				String opposingName = null;
 				boolean opposingNameSelected = false;
 				while(!opposingNameSelected) {
 					System.out.println("What is the opposing teams's name");
 	 				opposingName = scan.nextLine();
 	 				if(opposingName.length() < 1) {
 	 	 				System.out.println("Enter a team name");
 	 	 			}else {
 	 	 				opposingNameSelected = true;
 	 	 			}
 				}
 				
 				
 				this.model.setTeam2(new Team(opposingName, opposingCity));
 				boolean oppGenResponse = false;
 				while(!oppGenResponse) {
 					System.out.println("Would you like to auto-generate the opposing team (y/n)?");
 					String booleanAnswer = scan.nextLine();
 					
 					if(booleanAnswer.equals("Y") || booleanAnswer.equals("y") ) {
 						this.model.getTeam2().autoGenerate(this.model.getDb(),this.model.getCoachDb());
 						oppGenResponse = true;
 					}else if(booleanAnswer.equals("n") || booleanAnswer.equals("N")){
 						
 						selectTeam(this.model.getTeam2(), this.model.getDb().stream().filter(p -> p.isAssigned() == false).collect(Collectors.toCollection(ArrayList::new)), this.model.getCoachDb(), scan);
 						oppGenResponse = true;
 					}else {
 						System.out.println("type a valid response (y or n)");
 						continue;
 					}
 				}
 				this.model.getTeam2().toPrint();
 				this.model.setGs(new GameSimulator(this.model.getTeam(), this.model.getTeam2()));
 				this.model.getGs().simulateGame();
 			}
 			System.out.println("Would you like to quit? if so, type q. Otherwise, type anything else to continue.");
 			String quit = scan.nextLine();
 			if(quit.equals("Q") || quit.equals("q")) {
 				done += 1;
 			}else {
 				System.out.println("\n");
 				System.out.println("\n");
 				System.out.println("\n");
 			}
 			this.model.clearPlayerAssignments();
 			model.getTeam().clearAssigned();
 			model.getTeam2().clearAssigned();
 			this.model.clearCoachAssignments();
 		}
 		System.out.println("goodbye!");
	}
}
