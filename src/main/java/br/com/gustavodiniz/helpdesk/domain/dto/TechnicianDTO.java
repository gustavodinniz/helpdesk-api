package br.com.gustavodiniz.helpdesk.domain.dto;

import br.com.gustavodiniz.helpdesk.domain.Technician;
import br.com.gustavodiniz.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO {

    protected Integer id;

    @NotNull(message = "Required field")
    protected String name;

    @NotNull(message = "Required field")
    protected String cpf;

    @NotNull(message = "Required field")
    protected String email;

    @NotNull(message = "Required field")
    protected String password;

    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createdAt = LocalDate.now();

    public TechnicianDTO() {
        addProfiles(Profile.CLIENT);
    }

    public TechnicianDTO(Technician entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.profiles = entity.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.createdAt = entity.getCreatedAt();
        addProfiles(Profile.CLIENT);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfiles(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
