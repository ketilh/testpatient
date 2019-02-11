/**
 * 
 */
package no.hnikt.testpatient;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.hnikt.testpatient.model.Education;
import no.hnikt.testpatient.model.MaritalStatus;
import no.hnikt.testpatient.model.TestPatient;
import no.hnikt.testpatient.persistence.Name;
import no.hnikt.testpatient.persistence.NameRepository;
import no.hnikt.testpatient.persistence.Streetname;
import no.hnikt.testpatient.persistence.StreetnameRepository;

/**
 * @author Ketil Holden
 *
 */
@RestController
public class TestPatientGenerator {
	
	private static String[] STREETS = {"Gate", "Allè", "Veg", "Vei", "Plass", "Aveny", "Boulevard", "Sti"};
	
	private static String[] STREETS_DETERMINATIVE = {"gaten", "vegen", "veien", "plassen", "stien", "smuget", "rabben"};
	
	private static final Logger LOG = LoggerFactory.getLogger(TestPatientGenerator.class);
	
	private static final Integer SEX_MALE = Integer.valueOf(1);
	private static final Integer SEX_FEMALE = Integer.valueOf(2);
	
	Random rand = new Random(System.currentTimeMillis());
	
	@Autowired
	private NameRepository nameRepository;
	
	@Autowired
	private StreetnameRepository streetnameRepository;

	@RequestMapping("/generatePatient")
	public TestPatient generatePatient() {
		TestPatient patient = new TestPatient();
				
		Integer sex = rand.nextInt(10);
		if (sex < 5) {
			patient.setSex(SEX_MALE);
		} else {
			patient.setSex(SEX_FEMALE); 
		}

		patient.setBirthDate(generateDateOfBirth(0, 100));
		patient.setMaritalStatus(generateMaritalStatus(patient));
		patient.setEducation(generateEducation(patient));
		patient.setPhone(generatePhoneNo());
		patient.setAddress(generateStreetAddress());
		generateNames(patient);
		return patient; 
	}
	
	private String generateStreetAddress() {
		Integer x = rand.nextInt(100);
		if (x < 50) {
			List<Streetname> streets = streetnameRepository.findAllUndeterminative();
			return streets.get(rand.nextInt(streets.size())).getName() + " " + STREETS[rand.nextInt(STREETS.length)];
		} else if (x < 75) {
			List<Streetname> streets = streetnameRepository.findAllDeterminative();
			return streets.get(rand.nextInt(streets.size())).getName() + STREETS_DETERMINATIVE[rand.nextInt(STREETS_DETERMINATIVE.length)];			
		} else {
			List<Streetname> streets = streetnameRepository.findAllStandalone();
			return streets.get(rand.nextInt(streets.size())).getName();						
		}
	}
	
	private void generateNames(TestPatient patient) {
		if (patient.getSex().equals(SEX_MALE)) {
			List<Name> maleFirstnames = nameRepository.findAllMaleNames();
			patient.setFirstname(maleFirstnames.get(rand.nextInt(maleFirstnames.size())).getName());
		} else {
			List<Name> femaleFirstnames = nameRepository.findAllFemaleNames();
			patient.setFirstname(femaleFirstnames.get(rand.nextInt(femaleFirstnames.size())).getName());
		}

		List<Name> lastnames = nameRepository.findAllLastnames();
		patient.setLastname(lastnames.get(rand.nextInt(lastnames.size())).getName());

	}
	
	private String generatePhoneNo() {
		String phoneNumbers = String.valueOf(rand.nextInt(89999999) + 10000000);
		StringBuffer formatted = new StringBuffer(15); 
		LOG.debug("Phonenumberstring: {}",phoneNumbers);
		Integer type = rand.nextInt(3);
		
		Integer prefix = rand.nextInt(5); 
		if (prefix == 1) {
			formatted.append("0047 ");
		} else if (prefix == 2) {
			formatted.append("+47 ");
		}
		
		switch(type) {
			case 0:
				if (formatted.length() > 0) {
					formatted.setLength(formatted.length()-1); // remove space
				}
				formatted.append(phoneNumbers); 
				break;
			case 1: 
				formatted.append(phoneNumbers.substring(0, 3));
				formatted.append(" ");
				formatted.append(phoneNumbers.substring(3, 5));
				formatted.append(" ");
				formatted.append(phoneNumbers.substring(5, 8));
				break;
			case 2: 
				formatted.append(phoneNumbers.substring(0, 2));
				formatted.append(" ");
				formatted.append(phoneNumbers.substring(2, 4));
				formatted.append(" ");
				formatted.append(phoneNumbers.substring(4, 6));
				formatted.append(" ");
				formatted.append(phoneNumbers.substring(6, 8));
				break;
				
			default: 
				// no phone no
				formatted.setLength(0);
				break;
		}
		
		LOG.debug("Formatted phone number: {}",formatted.toString());
		
		return formatted.toString();
	}
	

	private MaritalStatus generateMaritalStatus(TestPatient patient) {
		
		if (patient.getAge() < 18) {
			return MaritalStatus.SINGLE;
		} else {
			return MaritalStatus.values()[rand.nextInt(MaritalStatus.values().length)];
		}
	}
	
	private Education generateEducation(TestPatient patient) {
		
		if (patient.getAge() < 12) {
			return Education.NOT_APPLICABLE;
		} else if (patient.getAge() < 18) { 
			return Education.PRIMARY;
		} else {
			return Education.values()[rand.nextInt(Education.values().length)];
		}
	}
	
	private LocalDate generateDateOfBirth(Integer minAge, Integer maxAge) {
		LocalDate startDate = LocalDate.now().minusYears(maxAge);
		LocalDate endDate = LocalDate.now().minusDays(2 + minAge * 365);

		long longDays = ChronoUnit.DAYS.between(startDate, endDate);
	    int days = (int) longDays;
	    if (days != longDays) {
	        throw new IllegalStateException("int overflow; too many years on this dude(ette)");
	    }
	    int day = rand.nextInt(days);
	    return startDate.plusDays(day);
	}
}
