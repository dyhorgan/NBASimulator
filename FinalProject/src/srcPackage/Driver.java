package srcPackage;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Driver {
	
	
	public static void main(String args[]) {
		ArrayList<Player> db = new FakeDataBase().getDatabase();
		ArrayList<Coach> coachDb = new CoachDB().getCoaches();
		Team team = new Team();
		Team team2 = new Team();
		GameSimulator simulator = new GameSimulator();
		Model model = new Model(db,coachDb,team,team2,simulator);
		Controller control = new Controller(model);
		control.go();
	}
}
