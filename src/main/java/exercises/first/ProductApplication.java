package exercises.first;

import exercises.first.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class ProductApplication {

    private EntityManager entityManager;
    private Scanner scanner;

    public void run() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        entityManager = entityManagerFactory.createEntityManager();

        while (true) {
            System.out.println("1. Dodaj produkt");
            System.out.println("2. Wyświetl wszystkie produkty");
            System.out.println("3. Wyświetl posortowane dane");
            System.out.println("0. Wyjście");

            scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    insertProduct();
                    break;
                case "2":
                    fetchAndShowAllProducts();
                    break;
                case "3":
                    fetchAndShowAllSortedProducts();
                    break;
                case "0":
                    scanner.close();
                    entityManager.close();
                    entityManagerFactory.close();
                    return;
                default:
                    System.err.println("Nie ma takiej opcji");
            }
        }
    }

    private void fetchAndShowAllSortedProducts() {
        System.out.println("Po czym chcesz sortować? n - po nazwie, c - po cenie");
        String userOption = scanner.nextLine();

        String orderBy = "";

        switch (userOption) {
            case "n":
                orderBy = "p.name";
                break;
            case "c":
                orderBy = "p.price";
                break;
            default:
                System.err.println("Nie ma takiej możliwości");
                return;
        }

        String query = "SELECT p FROM Product p ORDER BY " + orderBy;
        TypedQuery<Product> selectQuery = entityManager.createQuery(query, Product.class);
        selectQuery.getResultList().forEach(System.out::println);
    }

    private void fetchAndShowAllProducts() {
        TypedQuery<Product> selectQuery = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> resultList = selectQuery.getResultList();
        resultList.forEach(System.out::println);
    }

    private void insertProduct() {
        System.out.println("Podaj nazwę produktu");
        String productName = scanner.nextLine();

        System.out.println("Podaj cenę produktu");
        double productPrice = scanner.nextDouble();
        scanner.nextLine();

        Product product = new Product(productName, productPrice);

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }
}
