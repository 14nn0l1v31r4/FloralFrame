package br.edu.iff.ccc.bsi.webdev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.edu.iff.ccc.bsi.webdev.entities.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	
}
