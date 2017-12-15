package uk.ac.open.behavior.statechart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transition")
public class Transition {
	@Id
	@Column(name = "ID", nullable = false)
	private int ID;
	
	@Column(name = "stateID", nullable = false)
	private int stateID;
	
	@Column(name = "event", nullable = false)
	private String event;
	
	@Column(name = "guard", nullable = true)
	private String guard;
	
	@Column(name = "methodList", nullable = true)
	private String methodList;
	
	@Column(name = "nextState", nullable = false)
	private String nextState;

	public Transition(){}
	
	public Transition(int iD, int stateID, String event, String guard,
			String methodList, String nextState) {
		ID = iD;
		this.stateID = stateID;
		this.event = event;
		this.guard = guard;
		this.methodList = methodList;
		this.nextState = nextState;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getStateID() {
		return stateID;
	}
	public void setStateID(int stateID) {
		this.stateID = stateID;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getGuard() {
		return guard;
	}
	public void setGuard(String guard) {
		this.guard = guard;
	}
	public String getMethodList() {
		return methodList;
	}
	public void setMethodList(String methodList) {
		this.methodList = methodList;
	}
	public String getNextState() {
		return nextState;
	}
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

}
