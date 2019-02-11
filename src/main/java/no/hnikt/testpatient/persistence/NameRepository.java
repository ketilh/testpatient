/**
 * 
 */
package no.hnikt.testpatient.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ketil Holden
 *
 */
public interface NameRepository extends CrudRepository<Name, Long> {
	
	@Query("select o from Name o where lastname = 1")
	List<Name> findAllLastnames();

	@Query("select o from Name o where female = 0 and lastname = 0")
	List<Name> findAllMaleNames();

	@Query("select o from Name o where female = 1 and lastname = 0")
	List<Name> findAllFemaleNames();

}
