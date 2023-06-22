package basic_usage;

import basic_sample.Member;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class BasicOperation {

    static EntityManagerFactory emf;
    EntityManager em;


    @BeforeAll
    static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("h2database");
    }

    @AfterAll
    static void closeEntityManagerFactory() {
        emf.close();
    }

    @BeforeEach
    void createEntityManager(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        member = new Member();
        member.setId(id);
        member.setUsername(name);
        member.setAge(age);

    }

    @AfterEach
    void closeEntityManager(){
        em.getTransaction().rollback();
        if (em.isOpen()){
            em.close();
        }
    }
    Member member;
    String id = "id1";
    String name = "이름";
    int age = 10;


    @Test
    void insert(){
        em.persist(member);
        Member findedMember = em.find(Member.class, id);

        assertSame(id, findedMember.getId());
        assertSame(name, findedMember.getUsername());
        assertSame(age, findedMember.getAge());

    }

    @Test
    void update() {
        em.persist(member);
        member.setAge(20);
        Member findedMember = em.find(Member.class, id);
        assertSame(20, findedMember.getAge());
    }

    @Test
    void delete() {
        em.persist(member);
        em.remove(member);
        Member findedMember = em.find(Member.class, id);
        assertNull(findedMember);
    }


}
