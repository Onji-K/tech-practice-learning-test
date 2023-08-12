package jpa_test.relation_mapping.many_to_many;

import jpa_test.JpaTestSuper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class ManyToManyTest extends JpaTestSuper {

    @Test
    void manyToManyCascade(){
        Role role = new Role(1, "role1");
        Principal principal = new Principal(1, "user1", Set.of(role));

        em.persist(principal);
        em.flush();

        System.out.println("그다음 지우면");
        em.remove(principal);
        em.flush();
        System.out.println("role이 지워지나?");
        Role role1 = em.find(Role.class, 1);

        Assertions.assertNotNull(role1);
        /*
        결론 : many to many에서 cascade 속성을 주면, 매핑 테이블 뿐만이 아니라, 반대쪽 엔티티도 영향을 받는다.
         */


    }



}