package br.com.gustavodiniz.helpdesk.domain.dto;

import br.com.gustavodiniz.helpdesk.domain.Called;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class CalledDTO implements Serializable {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;

    @NotNull(message = "Field required")
    private Integer priority;

    @NotNull(message = "Field required")
    private Integer status;

    @NotNull(message = "Field required")
    private String title;

    @NotNull(message = "Field required")
    private String comments;

    @NotNull(message = "Field required")
    private Integer technician;

    @NotNull(message = "Field required")
    private Integer client;

    private String nameTechnician;

    private String nameClient;

    public CalledDTO() {
    }

    public CalledDTO(Called entity) {
        this.id = entity.getId();
        this.openingDate = entity.getOpeningDate();
        this.closingDate = entity.getClosingDate();
        this.priority = entity.getPriority().getCode();
        this.status = entity.getStatus().getCode();
        this.title = entity.getTitle();
        this.comments = entity.getComments();
        this.technician = entity.getTechnician().getId();
        this.client = entity.getClient().getId();
        this.nameTechnician = entity.getTechnician().getName();
        this.nameClient = entity.getClient().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
