package uk.ac.open.data.models;

import java.io.Serializable;

import javax.persistence.Column;

public class RelaxStatementsID implements Serializable{
	@Column(name = "requirementCode", nullable = false)
	private String requirementCode;
	
	@Column(name = "shallClause", nullable = false)
	private String shallClause;
	
	public RelaxStatementsID(){}
	public RelaxStatementsID(String requirementCode, String shallClause) {
		this.requirementCode = requirementCode;
		this.shallClause = shallClause;
	}

	@Override
	public boolean equals(Object o){
		return this.equals(o);
	}
	
	@Override
	public int hashCode(){
		return this.hashCode();
	}

	public String getRequirementCode() {
		return requirementCode;
	}

	public void setRequirementCode(String requirementCode) {
		this.requirementCode = requirementCode;
	}

	public String getShallClause() {
		return shallClause;
	}

	public void setShallClause(String shallClause) {
		this.shallClause = shallClause;
	}
	
	private static final long serialVersionUID = 1L;
}
