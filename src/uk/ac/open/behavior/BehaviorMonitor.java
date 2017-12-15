package uk.ac.open.behavior;

import java.util.HashMap;
import java.util.List;

import uk.ac.open.behavior.statechart.State;
import uk.ac.open.behavior.statechart.Transition;
import uk.ac.open.data.DBFactory;
import uk.ac.open.data.models.Instance;

public class BehaviorMonitor {
	private static BehaviorMonitor instance;
	private HashMap<Integer, BehaviorState> behaviorRTM;
	
	public static BehaviorMonitor getMonitor(){
		if(instance == null){
			instance = new BehaviorMonitor();
		}
		return instance;
	}
	
	private BehaviorMonitor(){
		initiateStates();
	}
	
	private void initiateStates(){
		behaviorRTM = new HashMap<Integer, BehaviorState>();
		
		List<Instance> instances = DBFactory.getDB().getAllInstances();
		for (Instance i : instances) {
			State initialState = DBFactory.getDB().getInitialState(i.getEntityID());
			BehaviorState bs = new BehaviorState(i.getID(),null,initialState, null);
			behaviorRTM.put(bs.getInstanceID(), bs);
		}
	}
	
	public void receiveEventMessage(EventMessage msg){
		BehaviorState bs = behaviorRTM.get(msg.getInstanceID());
		State previousState = bs.getActualState();
		Transition t = DBFactory.getDB().getTransition(bs.getActualState().getID(), msg.getEvent(), msg.getConditions());
		Instance i = DBFactory.getDB().getInstance(bs.getInstanceID());
		State newState = DBFactory.getDB().getState(i.getEntityID(), t.getNextState());
		bs = new BehaviorState(i.getID(), previousState, newState, msg);
		behaviorRTM.put(bs.getInstanceID(), bs);
		
		printStateChange(bs.getInstanceID(), previousState.getName());
	}
	
	private void printStateChange(int instanceID, String psStateName){
		BehaviorState bs = behaviorRTM.get(instanceID);
		System.out.println("---");
		System.out.println("InstanceID = "+bs.getInstanceID());
		System.out.println("Prev State = "+psStateName);
		System.out.println("New State = "+bs.getActualState().getName());
		System.out.println("Event = "+bs.getLastMessage().getEvent());
		System.out.println("---");
	}
	
	public BehaviorState getStatus(int instanceID){
		return instance.behaviorRTM.get(instanceID);
	}
}
