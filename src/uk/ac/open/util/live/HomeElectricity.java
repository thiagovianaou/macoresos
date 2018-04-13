package uk.ac.open.util.live;

public class HomeElectricity {

	private int HomeId;
	private double value;

	public HomeElectricity(int homeId, int value) {
		HomeId = homeId;
		this.value = value;
	}

	public int getHomeId() {
		return HomeId;
	}
	public void setHomeId(int homeId) {
		HomeId = homeId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
		if(this.value < 0) {
			this.value=0;
		}
		//just for data
		print();
	}
	
	public void print() {
		System.out.println("electricity-"+HomeId+";"+value);
	}
	
	
}
