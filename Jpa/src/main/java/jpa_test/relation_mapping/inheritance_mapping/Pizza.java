package jpa_test.relation_mapping.inheritance_mapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Pizza")
public class Pizza extends Item{

    private String topping;

    public Pizza() {
    }

    public Pizza(String name, String topping) {
        super(name);
        this.topping = topping;
    }

    public String getTopping() {
        return topping;
    }
}
