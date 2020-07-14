import domain.User;
import domain.UserType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(new User("Marcin", LocalDateTime.now(), UserType.GOLD));
        entityManager.persist(new User("Asia", LocalDateTime.now(), UserType.PREMIUM));
        entityManager.persist(new User("Ania", LocalDateTime.now(), UserType.PREMIUM));
        entityManager.persist(new User("Kamil", LocalDateTime.now(), UserType.GOLD));
        entityManager.persist(new User("Jan", LocalDateTime.now(), UserType.STANDARD));
        entityManager.persist(new User("Karol", LocalDateTime.now(), UserType.GOLD));

        entityManager.getTransaction().commit();

//        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE '%a'");

//        List resultList = query.getResultList();
//        for (Object result : resultList) {
//            User user = (User) result;
//            System.out.println(user.getName());
//        }

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE '%a'", User.class);

        List<User> resultList = query.getResultList();
        resultList.stream()
                .map(User::getName)
                .forEach(System.out::println);
    }
}
