package jpa_test.relation_mapping;


import jpa_test.JpaTestSuper;
import jpa_test.relation_mapping.inheritance_mapping.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("상속관계 매핑 테스트")
public class InheritanceTest extends JpaTestSuper {

    @Test
    @DisplayName("상속 관계 저장")
    void test(){
        Book book1 = new Book("book1");
        em.persist(book1);
        
    }


}
