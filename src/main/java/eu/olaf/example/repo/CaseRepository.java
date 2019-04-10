package eu.olaf.example.repo;

import eu.olaf.example.model.test.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseRepository extends JpaRepository<Case, Long> { }
