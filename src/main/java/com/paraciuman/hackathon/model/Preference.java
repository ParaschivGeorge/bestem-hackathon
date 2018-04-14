package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Objects;

enum pref{food,car_rental,church,bank,atm,museum,park,restaurant,stadium,hospital,zoo,supermarket,gas_station,establishment,
    finance,health,place_of_worship,room}

@Entity
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    private String preferinta;

    public Preference() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getPreferinta() {
        return preferinta;
    }

    public void setPreferinta(String preferinta) {
        this.preferinta = preferinta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return id == that.id &&
                Objects.equals(agenda, that.agenda) &&
                Objects.equals(preferinta, that.preferinta);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, agenda, preferinta);
    }
}
