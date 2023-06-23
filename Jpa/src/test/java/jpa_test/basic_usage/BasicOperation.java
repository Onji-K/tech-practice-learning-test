package jpa_test.basic_usage;

import jpa_test.basic_sample.User;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        user = new User();
        user.setId(id);
        user.setUsername(name);
        user.setAge(age);

    }

    @AfterEach
    void closeEntityManager(){
        em.getTransaction().rollback();
        if (em.isOpen()){
            em.close();
        }
    }
    User user;
    String id = "id1";
    String name = "이름";
    int age = 10;


    @Test
    void insert(){
        em.persist(user);
        User findedUser = em.find(User.class, id);

        assertSame(id, findedUser.getId());
        assertSame(name, findedUser.getUsername());
        assertSame(age, findedUser.getAge());

    }

    @Test
    void update() {
        em.persist(user);
        user.setAge(20);
        User findedUser = em.find(User.class, id);
        assertSame(20, findedUser.getAge());
    }

    @Test
    void delete() {
        em.persist(user);
        em.remove(user);
        User findedUser = em.find(User.class, id);
        assertNull(findedUser);
    }


}
