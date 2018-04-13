package uk.ac.open.util.live;
import java.util.ArrayList;

public class Home {

	private HomeElectricity he;
	private HomeTemperature ht;
	private ArrayList<PersonHomeCalories> phc;
	
	
	public Home(int homeId, int persons) {
		he = new HomeElectricity(homeId, 0);
		ht = new HomeTemperature(homeId, 0);
		phc = new ArrayList<PersonHomeCalories>();
		
		for(int i=0; i<persons; i++) {
			PersonHomeCalories phc1 = new PersonHomeCalories(homeId, i, 0);
			phc.add(phc1);
		}
	}
	
	public HomeElectricity getHe() {
		return he;
	}
	public void setHe(HomeElectricity he) {
		this.he = he;
	}
	public HomeTemperature getHt() {
		return ht;
	}
	public void setHt(HomeTemperature ht) {
		this.ht = ht;
	}
	public ArrayList<PersonHomeCalories> getPhc() {
		return phc;
	}
	public void setPhc(ArrayList<PersonHomeCalories> phc) {
		this.phc = phc;
	}
	
	public double getFamilyCalories() {
		double ret = 0;
		for (PersonHomeCalories personHomeCalories : phc) {
			ret = ret + personHomeCalories.getValue();
		}
		//just for data
		print(ret);
		
		return ret;
	}
	
	public void print(double familyCal) {
		System.out.println("family calories-"+he.getHomeId()+";"+familyCal);
	}
	
}
