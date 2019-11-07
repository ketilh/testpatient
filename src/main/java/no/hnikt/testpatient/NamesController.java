/**
 * 
 */
package no.hnikt.testpatient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import no.hnikt.testpatient.persistence.Name;
import no.hnikt.testpatient.persistence.NameRepository;

/**
 * @author knotilla
 *
 */
@RestController
public class NamesController {
	
	private static final Logger LOG = LoggerFactory.getLogger(NamesController.class);
	
	@Autowired
	private NameRepository nameRepository;

	@GetMapping("names")
	public ResponseEntity<List<Name>> getAllNames() {
		List<Name> list = StreamSupport.stream(nameRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()); 
		return new ResponseEntity<List<Name>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addname")
	public Name addName(@Validated @RequestBody Name name) {
		LOG.info("Name {} added", name.getName());
	    return nameRepository.save(name);
	}
	
	@GetMapping("name/{id}")
	public ResponseEntity<Name> getNameById(@PathVariable("id") Long id) {
		Optional<Name> name = nameRepository.findById(id);
		if (!name.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find that name dude - even though I looked everywhere!");
		}	
		return new ResponseEntity<Name>(name.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("name/{id}")
	public ResponseEntity<Void> deleteName(@PathVariable("id") Long id) {
		Optional<Name> name = nameRepository.findById(id);
		if (!name.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find that name dude - even though I looked everywhere!");
		}
		LOG.info("Deleting name NameId={}", name.get().getId());
		nameRepository.delete(name.get());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// also saves new ones right now.....
	@PutMapping("name")
	public ResponseEntity<Name> updateName(@RequestBody Name name) {
		nameRepository.save(name);
		LOG.info("Updating ");
		return new ResponseEntity<Name>(name, HttpStatus.OK);
	}
}
