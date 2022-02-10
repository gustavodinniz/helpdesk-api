package br.com.gustavodiniz.helpdesk.domain;

import br.com.gustavodiniz.helpdesk.domain.dto.ClientDTO;
import br.com.gustavodiniz.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_client")
public class Client extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Called> calleds = new ArrayList<>();

    public Client() {
        super();
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public Client(ClientDTO dto) {
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
