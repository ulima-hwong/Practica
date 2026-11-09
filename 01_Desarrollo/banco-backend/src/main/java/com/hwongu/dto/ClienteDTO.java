package com.hwongu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private Integer idCliente;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50, message = "{name.size}")
    private String nameCliente;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100, message = "{lastname.size}")
    private String lastNameCliente;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]{8}$", message = "{dni.format}")
    private String dniCliente;

    @NotNull
    @NotEmpty
    @Email(message = "{email.format}")
    @Size(min = 5, max = 120, message = "{email.size}")
    private String emailCliente;

    @NotNull
    private LocalDate birthDateCliente;

    @NotNull
    private boolean enabledCliente;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer idCliente, String nameCliente, String lastNameCliente, String dniCliente, String emailCliente, LocalDate birthDateCliente, boolean enabledCliente) {
        this.idCliente = idCliente;
        this.nameCliente = nameCliente;
        this.lastNameCliente = lastNameCliente;
        this.dniCliente = dniCliente;
        this.emailCliente = emailCliente;
        this.birthDateCliente = birthDateCliente;
        this.enabledCliente = enabledCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNameCliente() {
        return nameCliente;
    }

    public void setNameCliente(String nameCliente) {
        this.nameCliente = nameCliente;
    }

    public String getLastNameCliente() {
        return lastNameCliente;
    }

    public void setLastNameCliente(String lastNameCliente) {
        this.lastNameCliente = lastNameCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public LocalDate getBirthDateCliente() {
        return birthDateCliente;
    }

    public void setBirthDateCliente(LocalDate birthDateCliente) {
        this.birthDateCliente = birthDateCliente;
    }

    public boolean isEnabledCliente() {
        return enabledCliente;
    }

    public void setEnabledCliente(boolean enabledCliente) {
        this.enabledCliente = enabledCliente;
    }
}

