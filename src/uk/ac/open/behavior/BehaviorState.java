package uk.ac.open.behavior;

import uk.ac.open.behavior.statechart.State;

public class BehaviorState {
	private int instanceID;
	private State actualState;
	private State previousState;
	private EventMessage lastMessage;

	public BehaviorState(){}
	public BehaviorState(int instanceID, State previousState, State actualState, EventMessage lastMessage) {
		this.instanceID = instanceID;
		this.actualState = actualState;
		this.lastMessage = lastMessage;
		this.previousState = previousState;
	}
	
	public int getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}
	public State getActualState() {
		return actualState;
	}
	public void setActualState(State actualState) {
		this.actualState = actualState;
	}
	public EventMessage getLastMessage() {
		return lastMessage;
	}
	public void setLastMessage(EventMessage lastMessage) {
		this.lastMessage = lastMessage;
	}
	public State getPreviousState() {
		return previousState;
	}
	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}
}
