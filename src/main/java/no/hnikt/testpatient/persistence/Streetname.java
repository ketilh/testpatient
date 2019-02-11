/**
 * 
 */
package no.hnikt.testpatient.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author knotilla
 *
 */
@Entity
@Table(name = "streetnames")
public class Streetname implements Serializable{

	private static final long serialVersionUID = 6485486400550061001L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isDeterminative() {
		return determinative;
	}

	public void setDeterminative(boolean determinative) {
		this.determinative = determinative;
	}

	public boolean isStandalone() {
		return standalone;
	}

	public void setStandalone(boolean standalone) {
		this.standalone = standalone;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private String name; 
	
	@Column(name="determinative")
	private boolean determinative;
	
	@Column(name="standalone")
	private boolean standalone;
	
	
}
