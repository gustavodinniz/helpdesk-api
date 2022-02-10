package br.com.gustavodiniz.helpdesk.services;

import br.com.gustavodiniz.helpdesk.domain.Person;
import br.com.gustavodiniz.helpdesk.domain.Technician;
import br.com.gustavodiniz.helpdesk.domain.dto.TechnicianDTO;
import br.com.gustavodiniz.helpdesk.repositories.PersonRepository;
import br.com.gustavodiniz.helpdesk.repositories.TechnicianRepository;
import br.com.gustavodiniz.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.gustavodiniz.helpdesk.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Technician findById(Integer id) {
        Optional<Technician> technician = repository.findById(id);
        return technician.orElseThrow(() -> new EntityNotFoundException("Technician with id " + id + " not found"));
    }

    public List<Technician> findAll() {
        return repository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO) {
        technicianDTO.setId(null);
        technicianDTO.setPassword(passwordEncoder.encode(technicianDTO.getPassword()));
        validationByCpfAndEmail(technicianDTO);
        Technician entity = new Technician(technicianDTO);
        return repository.save(entity);
    }

    public Technician update(TechnicianDTO technicianDTO, Integer id) {
        technicianDTO.setId(id);
        Technician entity = findById(id);
        validationByCpfAndEmail(technicianDTO);
        entity = new Technician(technicianDTO);
        return repository.save(entity);
    }

    private void validationByCpfAndEmail(TechnicianDTO technicianDTO) {

        Optional<Person> entity = personRepository.findByCpf(technicianDTO.getCpf());

        if (entity.isPresent() && entity.get().getId() != technicianDTO.getId()) {
            throw new DataIntegrityViolationException("CPF already registered in the system");
        }

        entity = personRepository.findByEmail(technicianDTO.getEmail());
        if (entity.isPresent() && entity.get().getId() != technicianDTO.getId()) {
            throw new DataIntegrityViolationException("Email already registered in the system");
        }

    }

    public void delete(Integer id) {
        Technician entity = findById(id);
        if (entity.getCalleds().size() > 0) {
            throw new DataIntegrityViolationException("The technician has work orders and cannot be deleted");
        }
        repository.deleteById(id);
    }
}
