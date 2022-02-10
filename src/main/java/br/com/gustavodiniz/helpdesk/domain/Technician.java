package br.com.gustavodiniz.helpdesk.domain;

import br.com.gustavodiniz.helpdesk.domain.dto.TechnicianDTO;
import br.com.gustavodiniz.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_technician")
public class Technician extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Called> calleds = new ArrayList<>();

    public Technician() {
        super();
        addProfile(Profile.CLIENT);
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public Technician(TechnicianDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.profiles = dto.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.createdAt = dto.getCreatedAt();
    }

    public List<Called> getCalleds() {
        return calleds;
    }

    public void setCalleds(List<Called> calleds) {
        this.calleds = calleds;
    }
}
