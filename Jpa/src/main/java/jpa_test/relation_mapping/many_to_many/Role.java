package jpa_test.relation_mapping.many_to_many;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    private int id;

    private String value;

    public Role() {
    }

    public Role(int id, String value) {
        this.id = id;
        this.value = value;
    }
}
