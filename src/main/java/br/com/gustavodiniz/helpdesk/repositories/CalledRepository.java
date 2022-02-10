package br.com.gustavodiniz.helpdesk.repositories;

import br.com.gustavodiniz.helpdesk.domain.Called;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalledRepository extends JpaRepository<Called, Integer> {
}
