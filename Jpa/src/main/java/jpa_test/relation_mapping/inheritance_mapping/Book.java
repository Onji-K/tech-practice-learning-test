package jpa_test.relation_mapping.inheritance_mapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
public class Book extends Item{
    public Book(String name) {
        super(name);
    }
}
