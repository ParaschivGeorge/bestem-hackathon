package com.paraciuman.hackathon.model;

import javax.persistence.*;

enum friendRequest{accepted, pending, rejected};

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated
    private friendRequest friendReq;

    public Friend() {
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
    public friendRequest getFriendReq() {
        return friendReq;
    }

    public void setFriendReq(friendRequest friendReq) {
        this.friendReq = friendReq;
    }
}
