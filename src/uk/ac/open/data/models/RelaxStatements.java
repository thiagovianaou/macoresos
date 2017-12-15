package uk.ac.open.data.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "relaxStatements")
public class RelaxStatements {
	@EmbeddedId
	private RelaxStatementsID id;
	
	@Column(name = "relaxStatement", nullable = true)
	private String relaxStatement;

	@Column(name = "relaxParams", nullable = true)
	private String relaxParams;

	public RelaxStatements(){}
	public RelaxStatements(RelaxStatementsID id, String relaxStatement,
			String relaxParams) {
		this.id = id;
		this.relaxStatement = relaxStatement;
		this.relaxParams = relaxParams;
	}
	
	public RelaxStatementsID getId() {
		return id;
	}

	public void setId(RelaxStatementsID id) {
		this.id = id;
	}

	public String getRelaxStatement() {
		return relaxStatement;
	}

	public void setRelaxStatement(String relaxStatement) {
		this.relaxStatement = relaxStatement;
	}

	public String getRelaxParams() {
		return relaxParams;
	}

	public void setRelaxParams(String relaxParams) {
		this.relaxParams = relaxParams;
	}
}
