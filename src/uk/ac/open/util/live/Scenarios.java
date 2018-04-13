package uk.ac.open.util.live;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Scenarios {
	
	private static int maxElec = 270;
	private static int maxTemp = 270;
	private static int maxCal = 270;

	public static void Scenario(int houses, boolean familyCal, boolean neighElec) {
		ArrayList<Home> homes = new ArrayList<Home>();
		for(int i=0; i<houses; i++) {
			Home h = new Home(i, 3);
			homes.add(h);
		}
		
		//30 days
		for(int days=0; days<30; days++) {
			for(int houseId=0, houseCount=0; houseId<houses; houseId++) {
				switch (houseCount) {
				case 0:
					//routine 1
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 3); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					meal(homes, houseId, 5); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					meal(homes, houseId, 3); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					
					break;
				case 1:
					//routine 2
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 3); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 5); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					meal(homes, houseId, 5); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					
					break;
									
				case 2:
					//routine 3
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 7); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 7); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					meal(homes, houseId, 7); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					break;
					
				case 3:
					//routine 4
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 7); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					meal(homes, houseId, 5); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					meal(homes, houseId, 3); checkConflictCalories(homes, houseId, familyCal);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					randomTempBehaviour(homes, houseId); checkConflictTemp(homes, houseId);
					randomElecBehaviour(homes, houseId); checkConflictElec(homes, houseId, neighElec);
					break;
				}
				
				houseCount++;
				if(houseCount>=4) {
					houseCount=0;
				}
			}
		}
	}
	
	
	private static void checkConflictTemp(ArrayList<Home> homes, int houseId) {
		double temp = homes.get(houseId).getHt().getValue();
		if(temp > maxTemp) {
			System.out.println("Conflict; Temperature; House:"+houseId);
		}
	}
	
	private static void checkConflictElec(ArrayList<Home> homes, int houseId,boolean neighElec) {
		double elec = homes.get(houseId).getHe().getValue();
		if(elec > maxElec) {
			System.out.println("Conflict; Electricity; House:"+houseId);
		}
		
		if(neighElec) {
			double neibElec=0;
			for (Home home : homes) {
				neibElec += home.getHe().getValue();
				
				if(neibElec > (homes.size()*(maxElec*0.95))) {
					System.out.println("Conflict; Electricity; Neighborhood");
				}
				//just for data
				System.out.println("Neighborhood Electricity;"+neibElec);
			}
		}
		
	}
	
	private static void checkConflictCalories(ArrayList<Home> homes, int houseId,boolean familyCal) {
		
		for(int i=0; i< homes.get(houseId).getPhc().size(); i++) {
			double indCal = homes.get(houseId).getPhc().get(i).getValue();
			if(indCal > maxCal) {
				System.out.println("Conflict; Calories; House:"+houseId+"; Person: "+i);
			}
		}
		
		if(familyCal) {
			double familyCalories = homes.get(houseId).getFamilyCalories();
			if(familyCalories > homes.get(houseId).getPhc().size()*(maxCal*1.2)) {
				System.out.println("Conflict; Family Calories; House:"+houseId);
			}
		}
		
	}

	private static void meal(ArrayList<Home> homes, int houseId, int potency) {
		int randomNum;
		randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);

		if(randomNum > 3) {
			for(int persons=0; persons< homes.get(houseId).getPhc().size(); persons++) {
				randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
				homes.get(houseId).getPhc().get(persons).setValue(homes.get(houseId).getPhc().get(persons).getValue()-randomNum);
			}
		}else {
			for(int persons=0; persons< homes.get(houseId).getPhc().size(); persons++) {
				randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
				
				int mult = ThreadLocalRandom.current().nextInt(1, potency + 1);
				int div = ThreadLocalRandom.current().nextInt(1, potency + 1);
				randomNum = ThreadLocalRandom.current().nextInt(1, (potency+1) + 1);
				double x = randomNum * ThreadLocalRandom.current().nextInt(1, mult + 1);
				x /= ThreadLocalRandom.current().nextInt(1, div + 1);
				
				homes.get(houseId).getPhc().get(persons).setValue(homes.get(houseId).getPhc().get(persons).getValue()+x);
			}
			
		}
		
		homes.get(houseId).getFamilyCalories();
	}

	private static void randomElecBehaviour(ArrayList<Home> homes, int j) {
		int randomNum;
		randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		if(randomNum > 3) {
			randomNum = ThreadLocalRandom.current().nextInt(0, 11 + 1);
			homes.get(j).getHe().setValue(homes.get(j).getHe().getValue()-randomNum);
		}else {
			int mult = ThreadLocalRandom.current().nextInt(1, 7 + 1);
			int div = ThreadLocalRandom.current().nextInt(1, 5 + 1);
			randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
			double x = randomNum * ThreadLocalRandom.current().nextInt(1, mult + 1);
			x /= ThreadLocalRandom.current().nextInt(1, div + 1);
			homes.get(j).getHe().setValue(homes.get(j).getHe().getValue()+x);
		}
	}
	
	private static void randomTempBehaviour(ArrayList<Home> homes, int j) {
		int randomNum;
		randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
		if(randomNum > 2) {
			randomNum = ThreadLocalRandom.current().nextInt(0, 11 + 1);
			homes.get(j).getHt().setValue(homes.get(j).getHt().getValue()-randomNum);
		}else {
			randomNum = ThreadLocalRandom.current().nextInt(0, 11 + 1);
			homes.get(j).getHt().setValue(homes.get(j).getHt().getValue()+randomNum);
		}
	}
}
