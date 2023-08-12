package jpa_test.relation_mapping.many_to_one;

import jpa_test.JpaTestSuper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManyToOneTest extends JpaTestSuper {

    @Test
    void test() {
        Team team = new Team("1", "team1");
//        member1.setTeam(team);
//        team.getMembers().add(member1);
        em.persist(team);
        em.flush();
        Member member1 = new Member("1", "name");
        team.getMembers().add(member1);
        em.flush();

        Member member2 = new Member("2", "ndame");
        team.getMembers().add(member2);
        em.flush();
//        em.persist(team);

        System.out.println();
        /*
        cascade persist 는 자동으로 연관관계에 추가된 녀석을 persist 해준다는 뜻이지, 연관관계를 맺어주는 것은 아니다.
        이 말은 다시 말하면, member 객체에 대한 persist만 대신 호출해주는 것이다.
        따라서 member 에 의존 관계를 추가해주는 것은 여전히 개발자가 해줘야 한다.
         */

    }

}