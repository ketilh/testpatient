/**
 * 
 */
package no.hnikt.testpatient.model;

/**
 * @author Ketil Holden
 *
 */
public enum MaritalStatus {

	SINGLE(Integer.valueOf(1), "Enslig"),
	LIVEIN(Integer.valueOf(2), "Samboer"),
	MARRIED(Integer.valueOf(3), "Gift"),
	DIVORCED(Integer.valueOf(4), "Skilt"),
	WIDOW(Integer.valueOf(5), "Enke/enkemann"),
	NOT_APPLICABLE(Integer.valueOf(8), "Ikke aktuelt"),
	UNKNOWN(Integer.valueOf(9), "Ukjent");
	
	private Integer value;
	private String description; 
	
	private MaritalStatus(Integer value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public Integer getValue() {
		return value; 
	}
	
	public String getDescription() {
		return description; 
	}
}
