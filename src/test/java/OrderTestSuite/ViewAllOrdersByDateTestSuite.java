package OrderTestSuite;

import com.revature.bibimbop.order.OrderDao;
import com.revature.bibimbop.order.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ViewAllOrdersByDateTestSuite {
    OrderDao sut;

    @BeforeEach
    public void testPrep(){sut = new OrderDao();}

    @Test
    public void testOrderDaoViewAllByDateTestSuite_returnObjects(){

        OrderModel[] actualResult = sut.viewAllByDate("1/1/1");

        System.out.println(Arrays.toString(actualResult));
    }
}
