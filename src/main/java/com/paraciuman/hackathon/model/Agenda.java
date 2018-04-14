package com.paraciuman.hackathon.model;

import com.paraciuman.hackathon.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private Date saveDate;

    private Date startDate;

    private Date endDate;

    @OneToMany(mappedBy = "places", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Places> placesSet;

    public Agenda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
