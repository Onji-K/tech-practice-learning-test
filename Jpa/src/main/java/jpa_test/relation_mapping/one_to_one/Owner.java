package jpa_test.relation_mapping.one_to_one;

import javax.persistence.*;

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

}
