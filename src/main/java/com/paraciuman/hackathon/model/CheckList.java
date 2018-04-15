package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CheckList {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    private String comment;

    private Boolean checked;

    public CheckList() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckList)) return false;
        CheckList checkList = (CheckList) o;
        return Objects.equals(getAgenda(), checkList.getAgenda()) &&
                Objects.equals(getComment(), checkList.getComment()) &&
                Objects.equals(getChecked(), checkList.getChecked());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAgenda(), getComment(), getChecked());
    }
}
