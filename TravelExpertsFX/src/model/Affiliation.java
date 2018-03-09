package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the affiliations database table.
 * 
 */
@Entity
@Table(name="affiliations")
@NamedQuery(name="Affiliation.findAll", query="SELECT a FROM Affiliation a")
public class Affiliation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String affilitationId;

	private String affDesc;

	private String affName;

	public Affiliation() {
	}

	public String getAffilitationId() {
		return this.affilitationId;
	}

	public void setAffilitationId(String affilitationId) {
		this.affilitationId = affilitationId;
	}

	public String getAffDesc() {
		return this.affDesc;
	}

	public void setAffDesc(String affDesc) {
		this.affDesc = affDesc;
	}

	public String getAffName() {
		return this.affName;
	}

	public void setAffName(String affName) {
		this.affName = affName;
	}

}