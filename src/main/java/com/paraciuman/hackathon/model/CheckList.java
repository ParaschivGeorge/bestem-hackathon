package com.paraciuman.hackathon.model;

import javax.persistence.*;

@Entity
public class CheckList {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private String comment;
    private Boolean checked;

    public CheckList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
