import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        User user = new User();
        user.setName("Marcin");
        user.setCreationDate(LocalDateTime.now());

        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();

//        entityManager.getTransaction().begin();
//        user.setName("Karol");
//        entityManager.getTransaction().commit();

//        User user1 = new User();
//        user1.setId(1L);
//        user1.setName("Karol");
//        user1.setCreationDate(LocalDateTime.now());

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        User user1 = entityManager1.find(User.class, 1L);
//        entityManager.merge()

        entityManager1.getTransaction().begin();
        entityManager1.remove(user1);
        entityManager1.getTransaction().commit();
    }
}
