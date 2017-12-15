package uk.ac.open.communication;

import uk.ac.open.behavior.BehaviorMonitor;
import uk.ac.open.behavior.EventMessage;

public class EventsReceiver implements Runnable{

	private static EventsReceiver instance;
	private EventMessage msg;
	private boolean run;
	private boolean lock;
	
	private EventsReceiver(){
		msg = null;
		run = true;
		lock = false;
	}
	
	public static EventsReceiver getInstance(){
		if(instance == null){
			instance = new EventsReceiver();
		}
		return instance;
	}
	
	@Override
	public void run() {
		System.out.println("Events Receiver running...");
		while(run==true){
			if(msg!=null){
				BehaviorMonitor.getMonitor().receiveEventMessage(msg);
				lock=false;
				msg=null;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				stop();
				e.printStackTrace();
			}
		}
	}

	public boolean setEventReceived(String event) {
		if(!lock){
			msg = EventMessage.createEventMessage(event);
			lock = true;
			return true;
		}else{
			return false;
		}
	}
	
	public void stop(){
		run=false;
	}

}
