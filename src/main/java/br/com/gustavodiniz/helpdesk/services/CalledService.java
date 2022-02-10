package br.com.gustavodiniz.helpdesk.services;

import br.com.gustavodiniz.helpdesk.domain.Called;
import br.com.gustavodiniz.helpdesk.domain.Client;
import br.com.gustavodiniz.helpdesk.domain.Technician;
import br.com.gustavodiniz.helpdesk.domain.dto.CalledDTO;
import br.com.gustavodiniz.helpdesk.domain.enums.Priority;
import br.com.gustavodiniz.helpdesk.domain.enums.Status;
import br.com.gustavodiniz.helpdesk.repositories.CalledRepository;
import br.com.gustavodiniz.helpdesk.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CalledService {

    @Autowired
    private CalledRepository repository;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ClientService clientService;

    public Called findById(Integer id) {
        Optional<Called> entity = repository.findById(id);
        return entity.orElseThrow(() -> new EntityNotFoundException("Called with id " + id + " not found"));
    }

    public List<Called> findAll() {
        return repository.findAll();
    }

    public Called create(CalledDTO dto) {
        return repository.save(createOrUpdate(dto));
    }

    public Called update(Integer id, CalledDTO dto) {
        dto.setId(id);
        Called entity = findById(id);
        entity = createOrUpdate(dto);
        return repository.save(entity);
    }

    private Called createOrUpdate(CalledDTO calledDTO) {
        Technician technician = technicianService.findById(calledDTO.getTechnician());
        Client client = clientService.findById(calledDTO.getClient());

        Called called = new Called();

        if (calledDTO.getId() != null) {
            called.setId(calledDTO.getId());
        }

        if (calledDTO.getStatus().equals(2)) {
            called.setClosingDate(LocalDate.now());
        }

        called.setTechnician(technician);
        called.setClient(client);
        called.setPriority(Priority.toEnum(calledDTO.getPriority()));
        called.setStatus(Status.toEnum(calledDTO.getStatus()));
        called.setTitle(calledDTO.getTitle());
        called.setComments(calledDTO.getComments());

        return called;
    }


}
