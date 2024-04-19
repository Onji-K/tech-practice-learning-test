package jpa_test.merge_orphan_removal;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MergeOrphanRemovalTest {
    /*
    엔티티를 생성하고(준영속 상태)
    merge를 할 경우, cascade가 동작하는지 테스트
     */

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
    void createEntityManager() {
        em = emf.createEntityManager();

        em.getTransaction().begin();
    }

    @AfterEach
    void closeEntityManager() {
        em.getTransaction().rollback();
        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    void orphanRemovalTest() {
        // 미리 저장해놓는다.
        Department department = new Department();
        System.out.println("em.persist(department);");
        em.persist(department);
        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setName("이름");
        department.getEmployees().add(employee);
        System.out.println("em.persist(employee);");
        em.persist(employee);

        // id를 미리 알아 놓는다.
        Long employeeId = employee.getId();
        Long departmentId = department.getId();

        // 영속성 컨텍스트를 초기화한다.
        System.out.println("em.flush();");
        em.flush();
        em.clear();

        // department 를 만들어서 merge 한다.
        Department detachedDepartment = new Department();
        detachedDepartment.setId(departmentId);
        System.out.println("em.merge(detachedDepartment);");
        Department merged = em.merge(detachedDepartment);
        System.out.println("em.persist(merged);" + merged.getEmployees().size());
        em.persist(merged);// 여기서 과연 cascade가 동작할까?

        Assertions.assertEquals(0, merged.getEmployees().size());
        Assertions.assertEquals(0, em.find(Department.class, departmentId).getEmployees().size());


        // 영속성 컨텍스트를 초기화한다.
        System.out.println("em.flush();");
        em.flush();
        System.out.println("em.clear();");
        em.clear();

        System.out.println("em.find(Employee.class, " + employeeId + ");");
        Employee findedEmployee = em.find(Employee.class, employeeId);// 여기서 department가 null이 아닌지 확인한다.
        System.out.println("em.find(Department.class, " + departmentId + ");");
        Department findedDepartment = em.find(Department.class, departmentId);


        Assertions.assertNull(findedEmployee);
        Assertions.assertEquals(0, findedDepartment.getEmployees().size());

    }

    @Test // 리스트 순서가 바뀌어도 update가 발생할까?
    void reorderList() {
        // 미리 저장해놓는다.
        Department department = new Department();
        System.out.println("em.persist(department);");
        em.persist(department);
        Employee e1 = new Employee();
        e1.setDepartment(department);
        e1.setName("이름1");
        Employee e2 = new Employee();
        e2.setDepartment(department);
        e2.setName("이름2");

        department.getEmployees().add(e1);
        department.getEmployees().add(e2);

        long departmentId = department.getId();

        em.persist(e1);
        em.persist(e2);
        em.flush();
        em.clear();

        Department department1 = em.find(Department.class, departmentId);
        Employee removed = department1.getEmployees().remove(0);
        department1.getEmployees().add(removed);

        em.flush();
        em.clear();


    }
}