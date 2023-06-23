package jpa_test;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTestSuper {
    private static EntityManagerFactory emf;
    protected EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("h2database");
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }

    @BeforeEach
    void beforeEach() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
        em.close();
    }



}
