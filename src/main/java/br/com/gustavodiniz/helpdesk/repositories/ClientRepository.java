package br.com.gustavodiniz.helpdesk.repositories;

import br.com.gustavodiniz.helpdesk.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
