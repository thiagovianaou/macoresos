package uk.ac.open.assertions;

public class AssertionMonitor implements Runnable{
	//using the probe to check the assertions
	private static AssertionMonitor instance;
	private boolean run;
	
	private AssertionMonitor(){
		run = true;
	}
	
	public static AssertionMonitor getInstance(){
		if(instance == null){
			instance = new AssertionMonitor();
		}
		return instance;
	}
	
	public void run() {
		System.out.println("Assertion Monitor running...");
		while(run==true){
			if(true){
				//check assertions
				System.out.println("Monitoring assertions.....");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				stop();
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		run=false;
	}
	
}
