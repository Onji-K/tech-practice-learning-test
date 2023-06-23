package jpa_test.basic_usage;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionInstanceTest {

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
    }

    @AfterEach
    void closeEntityManager(){
        if (em.isOpen()){
            em.close();
        }
    }

    @Test
    @DisplayName("엔티티 매니저당 하나의 트랜잭션을 갖는다.")
    void oneTransactionPerOneEM(){
        EntityTransaction tx1 = em.getTransaction();
        EntityTransaction tx2 = em.getTransaction();

        //동일한 인스턴스 리턴
        assertSame(tx1, tx2);

        //서로 상태를 공유함
        tx1.begin();
        assertTrue(tx2.isActive());
        tx1.commit();
        assertFalse(tx2.isActive());
    }
}
