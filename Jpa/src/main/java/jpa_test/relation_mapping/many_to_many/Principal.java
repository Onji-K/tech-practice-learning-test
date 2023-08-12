package jpa_test.relation_mapping.many_to_many;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Principal {
    @Id
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns= @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Principal() {
    }

    public Principal(int id, String name, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }
}
