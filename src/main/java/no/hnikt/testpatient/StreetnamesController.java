/**
 * 
 */
package no.hnikt.testpatient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.hnikt.testpatient.persistence.Streetname;
import no.hnikt.testpatient.persistence.StreetnameRepository;

/**
 * @author Ketil Holden
 *
 */
@RestController
public class StreetnamesController {

private static final Logger LOG = LoggerFactory.getLogger(StreetnamesController.class);
	
	@Autowired
	private StreetnameRepository streetnameRepository;
	
	@GetMapping("streets")
	public ResponseEntity<List<Streetname>> getAllStreetNames() {
		List<Streetname> list = StreamSupport.stream(streetnameRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()); 
		return new ResponseEntity<List<Streetname>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addstreet")
	public Streetname createStreetname(@Validated @RequestBody Streetname name) {
		LOG.info("saving Streetname " + name);
	    return streetnameRepository.save(name);
	}
}
