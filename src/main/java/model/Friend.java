package model;

import javax.persistence.*;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name =  "id")
    private User user1;

    @OneToOne
    @JoinColumn(name =  "id")
    private User user2;
}
