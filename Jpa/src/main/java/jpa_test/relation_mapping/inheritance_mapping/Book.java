package jpa_test.relation_mapping.inheritance_mapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
public class Book extends Item{
    private String author;

    public Book() {
    }

    public Book(String name, String author) {
        super(name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
