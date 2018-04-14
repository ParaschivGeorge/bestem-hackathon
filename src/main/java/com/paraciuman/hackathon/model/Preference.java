package com.paraciuman.hackathon.model;

import javax.persistence.*;

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
}
