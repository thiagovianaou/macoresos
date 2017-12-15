package uk.ac.open.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instance")
public class Instance {
	@Column(name = "entityID", nullable = false)
	private String entityID;

	@Id
	@Column(name = "ID", nullable = false)
	private int ID;
	
	public Instance(){}
	
	public Instance(String entityID, int ID) {
		this.entityID = entityID;
		this.ID = ID;
	}

	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
