package MenuTestSuite;

import com.revature.bibimbop.menu.MenuDao;
import com.revature.bibimbop.menu.MenuModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class MenuDaoFindAllItemsTestSuite {

    MenuDao sut;

    @BeforeEach
    public void testPrep(){
        sut = new MenuDao();
    }

    @Test
    public void testMenuDaoFindAllItems_returnObjects() throws IOException {

        MenuModel[] actualResults = sut.findAllMenuItems();

        System.out.println(Arrays.toString(actualResults));
    }

}
