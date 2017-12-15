package uk.ac.open.behavior;

import uk.ac.open.util.Constants;

public class EventMessage {
	private int instanceID;
	private String event;
	private String conditions;
	private String action;
	
	public EventMessage(){}
	
	public EventMessage(int instanceID, String event, String conditions,
			String action) {
		this.instanceID = instanceID;
		this.event = event;
		this.conditions = conditions;
		this.action = action;
	}
	
	public static EventMessage createEventMessage(String event){
		//ex: 1;exercise;calories above limit;SET ExPlan = (laaa)
		String[] data = event.split(Constants.DELIMITER_EVENTS_MESSAGE);
		EventMessage r = new EventMessage(Integer.parseInt(data[0]), data[1], data[2], data[3]);
		return r;
	}
	
	public int getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
