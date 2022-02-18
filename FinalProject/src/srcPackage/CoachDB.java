package srcPackage;
import java.util.ArrayList;

public class CoachDB {
	ArrayList<Coach> coaches = new ArrayList<Coach>();
	public CoachDB(){
		Coach TomThibodeau = new Coach(5,9, "Tom Thibodeau");
		coaches.add(TomThibodeau);
		Coach GregPopovich = new Coach(7,8, "Greg Popovich");
		coaches.add(GregPopovich);
		Coach DocRivers = new Coach(6,7, "Doc Rivers");
		coaches.add(DocRivers);
		Coach SteveKerr = new Coach(8,7, "Steve Kerr");
		coaches.add(SteveKerr);
		Coach KurtRambis = new Coach(1,2, "Kurt Rambis");
		coaches.add(KurtRambis);
		Coach LukeWalton = new Coach(3,1, "Luke Walton");
		coaches.add(LukeWalton);
		Coach FrankVogel = new Coach(4,7, "Frank Vogel");
		coaches.add(FrankVogel);
		Coach TerryStotts = new Coach(7,3, "Terry Stotts");
		coaches.add(TerryStotts);
	}
	public ArrayList<Coach> getCoaches() {
		return this.coaches;
	}
}
