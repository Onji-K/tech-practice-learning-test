package jpa_test.relation_mapping.many_to_one;

import javax.persistence.*;

@Table(name = "MEMBER")
@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    protected Member() {
    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Team getTeam() {
        return team;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
