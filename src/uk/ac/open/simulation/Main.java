package uk.ac.open.simulation;

import java.io.IOException;

import uk.ac.open.assertions.AssertionMonitor;
import uk.ac.open.communication.EventsReceiver;
import uk.ac.open.communication.Register;

public class Main {
	public static void main(String[] args) throws IOException {
		Register.register(
				"ANALYSEME",
				1,
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/RELAX/AM.rtf",
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/STATECHARTS/AM.csv",
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/MONITORING/AM.rtf",
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/MAPs/AM.rtf"
				);
		
		Register.register(
				"HOMEHUB",
				2,
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/RELAX/HH.rtf",
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/STATECHARTS/HH.csv",
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/MONITORING/HH.rtf",
				"/Users/ThiagoViana/Desktop/Work/Implementation-Conflict Identification/FILES/MAPs/HH.rtf"
				);
		
		new Thread(EventsReceiver.getInstance()).start();
		new Thread(AssertionMonitor.getInstance()).start();

		EventsReceiver.getInstance().setEventReceived("1;exercise proposal;calories above limit;SET ExPlan = (laaa)");
	}

}
