package uk.ac.open.behavior.statechart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import uk.ac.open.util.Constants;

@Entity
@Table(name = "state")
public class State {
	@Id
	@Column(name = "ID", nullable = false)
	private int ID;
	
	@Column(name = "entityID", nullable = false)
	private String entityID;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "type", nullable = false)
	private int type;
	
	public State(){}
	public State(int iD, String entityID, String name, int type) {
		ID = iD;
		this.entityID = entityID;
		this.name = name;
		this.type = type;
	}

	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getEntityID() {
		return entityID;
	}
	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public static int getType(String type){
		int t = 1;
		if(type.equals(Constants.INITIAL_STATE)){
			t = 0;
		} else if(type.equals(Constants.NORMAL_STATE)){
			t = 1;
		} else if(type.equals(Constants.FINAL_STATE)){
			t = 2;
		}
		return t;
	}
}
