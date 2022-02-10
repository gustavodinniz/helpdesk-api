package br.com.gustavodiniz.helpdesk.repositories;

import br.com.gustavodiniz.helpdesk.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
}
