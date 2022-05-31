package OrderTestSuite;

import com.revature.bibimbop.order.OrderDao;
import com.revature.bibimbop.order.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ViewAllOrdersTestSuite {

    OrderDao sut;

    @BeforeEach
    public void testPrep(){sut = new OrderDao();}

    @Test
    public void testOrdersDaoViewAllOrders_returnObjects(){

        OrderModel[] actualResult = sut.viewAllOrders();

        System.out.println(Arrays.toString(actualResult));

    }
}
