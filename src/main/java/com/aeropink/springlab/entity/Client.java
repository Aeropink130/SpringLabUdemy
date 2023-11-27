package com.aeropink.springlab.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table(name = "clients")
@Entity
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "client_id", columnDefinition = "BINARY(16)")
    private UUID clientId;

    private String fullName;
    private String email;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<ClientApartment> apartments;

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ClientApartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<ClientApartment> apartments) {
        this.apartments = apartments;
    }
}