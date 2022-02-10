package br.com.gustavodiniz.helpdesk.services;

import br.com.gustavodiniz.helpdesk.domain.Client;
import br.com.gustavodiniz.helpdesk.domain.Person;
import br.com.gustavodiniz.helpdesk.domain.dto.ClientDTO;
import br.com.gustavodiniz.helpdesk.repositories.ClientRepository;
import br.com.gustavodiniz.helpdesk.repositories.PersonRepository;
import br.com.gustavodiniz.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.gustavodiniz.helpdesk.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Client findById(Integer id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client create(ClientDTO clientDTO) {
        clientDTO.setId(null);
        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        validationByCpfAndEmail(clientDTO);
        Client entity = new Client(clientDTO);
        return repository.save(entity);
    }

    public Client update(ClientDTO clientDTO, Integer id) {
        clientDTO.setId(id);
        Client entity = findById(id);
        validationByCpfAndEmail(clientDTO);
        entity = new Client(clientDTO);
        return repository.save(entity);
    }

    private void validationByCpfAndEmail(ClientDTO clientDTO) {

        Optional<Person> entity = personRepository.findByCpf(clientDTO.getCpf());

        if (entity.isPresent() && entity.get().getId() != clientDTO.getId()) {
            throw new DataIntegrityViolationException("CPF already registered in the system");
        }

        entity = personRepository.findByEmail(clientDTO.getEmail());
        if (entity.isPresent() && entity.get().getId() != clientDTO.getId()) {
            throw new DataIntegrityViolationException("Email already registered in the system");
        }

    }

    public void delete(Integer id) {
        Client entity = findById(id);
        if (entity.getCalleds().size() > 0) {
            throw new DataIntegrityViolationException("The client has work orders and cannot be deleted");
        }
        repository.deleteById(id);
    }
}
