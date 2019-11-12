package no.hnikt.testpatient.model;

public enum Education {
	KINDERGARTEN(Integer.valueOf(0), "Barnehage"),
	PRIMARY(Integer.valueOf(1), "Grunnskole"),
	BACHELOR(Integer.valueOf(2), "Høyskole/Bachelor"),
	// TODO check and add the rest
	NOT_APPLICABLE(Integer.valueOf(8), "Ikke aktuelt"),
	UNKNOWN(Integer.valueOf(9), "Ukjent"); 
	
	private Integer value;
	private String description; 
	
	private Education(Integer value, String description) {
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
