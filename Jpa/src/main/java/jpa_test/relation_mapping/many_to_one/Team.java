package jpa_test.relation_mapping.many_to_one;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "TEAM")
@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    protected Team() {
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }


    public void setName(String name) {
        this.name = name;
    }
}
