import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static final String GRINGOTTS_DB_NAME = "gringotts";
    private static final String SALES_DB_NAME = "sales";
    private static final String UNIVERSITY_DB_NAME = "university";
    private static final String PAYMENT_DB_NAME = "payment";
    private static final String HOSPITAL_DB_NAME = "hospital";
    private static final String BOOKMAKER_SYSTEM_DB_NAME = "bk_system";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(BOOKMAKER_SYSTEM_DB_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);

        engine.run();


    }
}
