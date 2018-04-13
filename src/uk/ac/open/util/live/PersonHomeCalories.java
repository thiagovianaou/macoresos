package uk.ac.open.util.live;

public class PersonHomeCalories {
	private int HomeId;
	private int PersonId;
	private double value;

	public PersonHomeCalories(int homeId, int personId, int value) {
		HomeId = homeId;
		PersonId = personId;
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
	public int getPersonId() {
		return PersonId;
	}
	public void setPersonId(int personId) {
		PersonId = personId;
	}
	
	public void print() {
		System.out.println("calories-"+HomeId+"-"+PersonId+";"+value);
	}
	
	
}
