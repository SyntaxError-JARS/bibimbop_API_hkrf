package OrderTestSuite;

import com.revature.bibimbop.order.OrderDao;
import com.revature.bibimbop.order.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDaoCreateOrderTestSuite {

    OrderDao sut;

    @BeforeEach
    public void testPrep(){
        sut = new OrderDao();
    }

    @Test
    public void testCreateCustomOrderForOrdersDao_returnObject(){

        OrderModel actualResult = sut.createCustomOrder(4, "item1", "test", 0, "10/01/1011", "test");
        
        System.out.println(actualResult);

    }
}
