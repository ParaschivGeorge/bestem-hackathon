package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    private String email;

    private String name;

    private String pictureUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agenda> agendas;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friend> friends;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPictureUrl(), user.getPictureUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getEmail(), getName(), getPictureUrl());
    }
}

//AIzaSyBcxpOqVpsm6K7XWRSL0tCWfgQE29OlZlY