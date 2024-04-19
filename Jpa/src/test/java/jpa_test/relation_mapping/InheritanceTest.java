package jpa_test.relation_mapping;


import jpa_test.JpaTestSuper;
import jpa_test.relation_mapping.inheritance_mapping.Book;
import jpa_test.relation_mapping.inheritance_mapping.Item;
import jpa_test.relation_mapping.inheritance_mapping.Pizza;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("상속관계 매핑 테스트")
public class InheritanceTest extends JpaTestSuper {

    @Test
    @DisplayName("상속 관계 저장")
    void test(){
        Book book1 = new Book("book1", "author1");
        Pizza pizza = new Pizza("pizza1", "topping1");

        em.persist(book1);
        em.persist(pizza);

        em.flush();
        em.clear();


        em.find(Item.class, book1.getId());
        em.flush();
        System.out.println("--------------------");
//        Book book = book1;
//        String author = book.getAuthor();
//        System.out.println(author);

    }


}
