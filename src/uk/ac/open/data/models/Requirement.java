package uk.ac.open.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "requirement")
public class Requirement {
	@Id
	@Column(name = "requirementCode", nullable = false)
	private String requirementCode;
	
	@Column(name = "entityID", nullable = false)
	private String entityID;
	
	@Column(name = "type", nullable = false)
	private int type;
	
	@Column(name = "priority", nullable = false)
	private int priority;

	public Requirement(){}
	public Requirement(String requirementCode, String entityID, int type,
			int priority) {
		this.requirementCode = requirementCode;
		this.entityID = entityID;
		this.type = type;
		this.priority = priority;
	}
	
	public String getRequirementCode() {
		return requirementCode;
	}

	public void setRequirementCode(String requirementCode) {
		this.requirementCode = requirementCode;
	}

	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
