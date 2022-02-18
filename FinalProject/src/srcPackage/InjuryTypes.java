package srcPackage;

public enum InjuryTypes {
	Knee{
		public String toString(){
			return "Knee Injury";
		}
	}, 
	Ankle{
		public String toString(){
			return "Ankle Injury";
		}
	}, Calf{
		public String toString(){
			return "Calf Injury";
		}
	},Foot{
		public String toString(){
			return "Foot Injury";
		}
	}, Shoulder{
		public String toString(){
			return "Shoulder Injury";
		}
	};
}
