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
 * @author Ketil Holden
 *
 */

@Entity
@Table(name = "names")
public class Name implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private String name; 
	
	@Column(name="lastname")
	private boolean isLastName;
	
	@Column(name="female")
	private boolean isFemale; 
	
	
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

	public boolean isLastName() {
		return isLastName;
	}

	public void setLastName(boolean isLastName) {
		this.isLastName = isLastName;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}

	private static final long serialVersionUID = 1984672015546169351L;

}
