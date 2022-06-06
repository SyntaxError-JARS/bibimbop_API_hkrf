import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.revature.bibimbop.util.HibernateUtil.getSession;


public class HibernateTestSuite {

    @Test
    public void test_getSession_givenProvidedCredentials_returnValidConnection(){
        // Arrange & Acting
        try(Session session = getSession()){
            System.out.println(session);

            // Assert
            Assertions.assertNotNull(session);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
