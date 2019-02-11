package no.hnikt.testpatient.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StreetnameRepository extends CrudRepository<Streetname, Long> {
	
	@Query("select o from Streetname o where determinative = 0 and standalone = 0")
	List<Streetname> findAllUndeterminative();

	@Query("select o from Streetname o where standalone = 1")
	List<Streetname> findAllStandalone();

	@Query("select o from Streetname o where determinative = 1 and standalone = 0")
	List<Streetname> findAllDeterminative();

}

