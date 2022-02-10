package br.com.gustavodiniz.helpdesk.controllers;

import br.com.gustavodiniz.helpdesk.domain.Called;
import br.com.gustavodiniz.helpdesk.domain.dto.CalledDTO;
import br.com.gustavodiniz.helpdesk.services.CalledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/calleds")
public class CalledController {

    @Autowired
    private CalledService service;

    @GetMapping(value = "{id}")
    public ResponseEntity<CalledDTO> findById(@PathVariable Integer id) {
        Called entity = service.findById(id);
        return ResponseEntity.ok().body(new CalledDTO(entity));
    }

    @GetMapping
    public ResponseEntity<List<CalledDTO>> findAll() {
        List<Called> list = service.findAll();
        List<CalledDTO> listDTO = list.stream().map(CalledDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<CalledDTO> create(@Valid @RequestBody CalledDTO dto) {
        Called entity = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CalledDTO> update(@PathVariable Integer id, @Valid @RequestBody CalledDTO dto) {
        Called entity = service.update(id, dto);
        return ResponseEntity.ok().body(new CalledDTO(entity));
    }
}
