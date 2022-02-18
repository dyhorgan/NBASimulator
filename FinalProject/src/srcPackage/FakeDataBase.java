package srcPackage;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

public class FakeDataBase {
	
	ArrayList<Player> fakeDataBase = new ArrayList<Player>();
	/*
	 * This creates the database of players. It's only called "fake" because eventually I planned on implementing a Postgres data
	 * base, never got that far
	 */
	public FakeDataBase(){
//		Player JuliusRandle = new Player(int minutes, int points, int assists, int steals, int blocks, int rebounds, int overall, Team team,
//				String position, String name);
		
		Player JuliusRandle = new Player(37.6,24.1,6,.9,.3,10.2,82,95,null,PositionTypes.PF, "Julius Randle", new ArrayList<Player>());
		fakeDataBase.add(JuliusRandle);
		Player RJBarrett = new Player(34.9,17.6,3,.7,.3,5.8,85,80,null,PositionTypes.SF, "RJ Barrett", new ArrayList<Player>());
		fakeDataBase.add(RJBarrett);
		Player EvanFournier = new Player(30.0,17.1,3.4,1.1,.5,3,70,83,null,PositionTypes.SG, "Evan Fournier", new ArrayList<Player>());
		fakeDataBase.add(EvanFournier);
		Player NerlensNoel = new Player(24.2,5.1,.7,1.1,2.2,6.4,92,77,null,PositionTypes.C, "Nerlens Noel", new ArrayList<Player>());
		JuliusRandle.addChemistryTeammate(NerlensNoel, true);
		JuliusRandle.addChemistryTeammate(RJBarrett, true);
		RJBarrett.addChemistryTeammate(NerlensNoel, true);
		fakeDataBase.add(NerlensNoel);
		Player DerrickRose = new Player(25.6,13.7,4.2,1,.4,2.6,67,86,null,PositionTypes.PG, "Derrick Rose", new ArrayList<Player>());
		fakeDataBase.add(DerrickRose);
		Player QuentinGrimes = new Player(16.4,6.9,1,.7,.15,2.85,76,70,null,PositionTypes.SG, "Quentin Grimes", new ArrayList<Player>());
		fakeDataBase.add(QuentinGrimes);
		Player DeuceMcBride = new Player(34.2,14.9,4.8,1.9,.3,3.9,80,68,null,PositionTypes.PG, "Deuce McBride", new ArrayList<Player>());
		fakeDataBase.add(DeuceMcBride);
		Player MitchellRobinson = new Player(27.5,8.3,.5,1.1,1.5,8.1,93,79,null,PositionTypes.C, "Mitchell Robinson", new ArrayList<Player>());
		fakeDataBase.add(MitchellRobinson);
		Player AlecBurks = new Player(25.6,12.7,2.2,.6,.3,4.6,72,78,null,PositionTypes.SG, "Alec Burks", new ArrayList<Player>());
		fakeDataBase.add(AlecBurks);
		Player ImmanuelQuickley = new Player(19.4,10.4,2,.5,.2,2.1,68,76,null,PositionTypes.PG, "Immanuel Quickley", new ArrayList<Player>());
		fakeDataBase.add(ImmanuelQuickley);
		Player ObiToppin = new Player(11,5.1,.5,.3,.2,2.2,70,72,null,PositionTypes.PF, "Obi Toppin", new ArrayList<Player>());
		fakeDataBase.add(ObiToppin);
		Player TajGibson = new Player(20.8,5.4,.8,.7,1.1,5.6,87,74,null,PositionTypes.C, "Taj Gibson", new ArrayList<Player>());
		fakeDataBase.add(TajGibson);
		Player KevinKnox = new Player(11,3.9,.5,.3,.1,1.5,70,65,null,PositionTypes.SF, "Kevin Knox", new ArrayList<Player>());
		fakeDataBase.add(KevinKnox);
		Player LucaVildoza = new Player(24.5,8.5,3.5,1.6,.1,1.9,67,68,null,PositionTypes.PG, "Luca Vildoza", new ArrayList<Player>());
		fakeDataBase.add(LucaVildoza);
		Player JerichoSims = new Player(24.5,8.2,.7,.7,1.1,7.2,85,70,null,PositionTypes.C, "Jericho Sims", new ArrayList<Player>());
		fakeDataBase.add(JerichoSims);
		Player JaysonTatum = new Player(35.8,26.4,4.3,1.2,.5,7.4,85,96,null,PositionTypes.PF, "Jayson Tatum", new ArrayList<Player>());
		fakeDataBase.add(JaysonTatum);
		Player AlHorford = new Player(27.9,9.2,3.4,.9,.9,6.7,87,77,null,PositionTypes.C, "Al Horford", new ArrayList<Player>());
		fakeDataBase.add(AlHorford);
		Player JaylenBrown = new Player(34.5,24.7,3.4,1.2,.6,6,85,95,null,PositionTypes.SF, "Jaylen Brown", new ArrayList<Player>());
		fakeDataBase.add(JaylenBrown);
		JaysonTatum.addChemistryTeammate(AlHorford, true);
		JaysonTatum.addChemistryTeammate(JaylenBrown, true);
		JaysonTatum.addChemistryTeammate(EvanFournier, true);
		JaylenBrown.addChemistryTeammate(EvanFournier, true);
		Player KrisDunn = new Player(24.9,7.3,3.4,2,.3,3.6,86,69,null,PositionTypes.PG, "Kris Dunn", new ArrayList<Player>());
		fakeDataBase.add(KrisDunn);
		Player BrunoFernando = new Player(12.7,4.3,.9,.3,.3,3.5,75,64,null,PositionTypes.C, "Bruno Fernando", new ArrayList<Player>());
		fakeDataBase.add(BrunoFernando);
		Player MarcusSmart = new Player(32.9,13.1,5.7,1.5,.5,3.5,88,84,null,PositionTypes.PG, "Marcus Smart", new ArrayList<Player>());
		fakeDataBase.add(MarcusSmart);
		Player JoshRichardson = new Player(30.3,11.1,2.6,1,.4,3.3,84,77,null,PositionTypes.SG, "Josh Richardson", new ArrayList<Player>());
		fakeDataBase.add(JoshRichardson);
		Player RomeoLangford = new Player(15.7,5.1,.7,.3,.3,1.9,78,65,null,PositionTypes.SG, "Romeo Langford", new ArrayList<Player>());
		fakeDataBase.add(RomeoLangford);
		Player RobertWilliams = new Player(18.9,7,1.8,.8,1.8,6.9,90,76,null,PositionTypes.C, "Robert Williams", new ArrayList<Player>());
		fakeDataBase.add(RobertWilliams);
		Player AaronNesmith = new Player(14.5,5.7,.5,.3,.2,2.8,73,71,null,PositionTypes.SF, "Aaron Nesmith", new ArrayList<Player>());
		fakeDataBase.add(AaronNesmith);
		Player GrantWilliams = new Player(18.1,5.7,1,.5,.4,2.8,80,71,null,PositionTypes.PF, "Grant Williams", new ArrayList<Player>());
		fakeDataBase.add(GrantWilliams);
		Player JabariParker = new Player(12.7,4.5,.8,.1,.4,3.2,69,70,null,PositionTypes.SF, "Jabari Parker", new ArrayList<Player>());
		fakeDataBase.add(JabariParker);
		Player PaytonPritchard = new Player(19.2,8.7,1.8,.6,.1,2.4,65,72,null,PositionTypes.PG, "Payton Pritchard", new ArrayList<Player>());
		fakeDataBase.add(PaytonPritchard);
		Player CarsenEdwards = new Player(8.9,4,.5,.2,0.1,.8,65,64,null,PositionTypes.PG, "Carsen Edwards", new ArrayList<Player>());
		fakeDataBase.add(CarsenEdwards);
		
		this.sort(this.fakeDataBase);
	}
	
	public ArrayList<Player> getDatabase(){
		return this.fakeDataBase;
	}
	
	public void sort(ArrayList<Player> db) {
		Collections.sort(db, new Comparator<Player>() {
			
			public int compare(Player p1, Player p2) {
				return p1.getPosition().compareTo(p2.getPosition());
			}
		});
	}
	
	
}
