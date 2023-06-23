package jpa_test.relation_mapping;

import jpa_test.JpaTestSuper;
import jpa_test.relation_mapping.many_to_one.Member;
import jpa_test.relation_mapping.many_to_one.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CRUD 테스트 :: 연관관계 매핑")
public class CRUDTest extends JpaTestSuper {
    @Test
    @DisplayName("CREATE :: 연관관계가 있는 엔티티 생성")
    void create1(){
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member foundedMember = em.find(Member.class, "member1");

        assertNotNull(foundedMember);
        assertSame(member1.getId(), foundedMember.getId());
    }

    @Test
    @DisplayName("CREATE :: 일단 생성한 후 연관관계 맺기")
    void create2(){
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        em.persist(member1);
        //persist 후에 set
        member1.setTeam(team1);

        Member foundedMember = em.find(Member.class, "member1");

        assertNotNull(foundedMember);
        assertSame(member1.getId(), foundedMember.getId());
        assertSame(member1.getTeam().getId(), foundedMember.getTeam().getId());
    }

    @Test
    void jpql(){
        //팀1에 소속된 모든 회원 만들기
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member1);
        em.persist(member2);

        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member: resultList){
            System.out.println("[query] member.username=" +
                    member.getUsername());
        }
        assertTrue(resultList.contains(member1));
        assertTrue(resultList.contains(member2));
    }

    @Test
    @DisplayName("jpql 후 롤백")
    void jpqlTransaction(){
        jpql();
        em.getTransaction().rollback();
        List<Member> resultList = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        assertSame(0, resultList.size());
        //어라? JPQL 나왔는데 롤백이 되네?
        //데이터베이스 자체의 트랜잭션 롤백
    }

    @Test
    @DisplayName("연관 관계를 끊을 때는 null을 넣어준다.")
    void update(){
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        member1.setTeam(null);

        Member foundedMember = em.find(Member.class, "member1");

        assertNotNull(foundedMember);
        assertNull(foundedMember.getTeam());
    }

    @Test
    @DisplayName("자동으로 one to many 쪽에는 반영이 안된다.")
    void read(){
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        Team foundedTeam = em.find(Team.class, "team1");
        assertSame(foundedTeam.getMembers().size() , 0);
    }

    @Test
    @DisplayName("객체 관계를 고려한 삽입")
    void save(){
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        team1.getMembers().add(member1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        team1.getMembers().add(member2);
        em.persist(member2);

        Team foundedTeam = em.find(Team.class, "team1");
        assertSame(foundedTeam.getMembers().size() , 2);
    }
    



}
