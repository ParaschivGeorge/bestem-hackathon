package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private Date saveDate;

    private Date startDate;

    private Date endDate;

    private String location;

    private List<Day> days;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Place> places;

    public Set<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Preference> preferences) {
        this.preferences = preferences;
    }

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Preference> preferences;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CheckList> checkLists;

    public Agenda() {
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDay(List<Day> days) {
        this.days = days;
    }

    public Set<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(Set<CheckList> checkLists) {
        this.checkLists = checkLists;
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


    public Set<Place> getPlaces() {

        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return id == agenda.id &&
                Objects.equals(user, agenda.user) &&
                Objects.equals(startDate, agenda.startDate) &&
                Objects.equals(endDate, agenda.endDate) &&
                Objects.equals(location, agenda.location) &&
                Objects.equals(days, agenda.days) &&
                Objects.equals(preferences, agenda.preferences) &&
                Objects.equals(checkLists, agenda.checkLists);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, startDate, endDate, location, days, places, preferences, checkLists);
    }

}
