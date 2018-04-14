package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agenda> agendas;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friend> friends;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }
}

//AIzaSyBcxpOqVpsm6K7XWRSL0tCWfgQE29OlZlY