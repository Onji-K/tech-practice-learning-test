package jpa_test.relation_mapping.one_to_one;

import javax.persistence.*;

@Table(name = "LOCKER")
@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;


    protected Locker() {
    }

    public Locker(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
