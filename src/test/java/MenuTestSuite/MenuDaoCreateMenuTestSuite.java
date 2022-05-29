package MenuTestSuite;

import com.revature.bibimbop.menu.MenuDao;
import com.revature.bibimbop.menu.MenuModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuDaoCreateMenuTestSuite {

    MenuDao sut;

    @BeforeEach
    public void testPrep(){
        sut = new MenuDao();
    }

    @Test
    public void testCreateNewMenuItemForMenuDao_returnsNewMenuObject(){


        MenuModel firstResult = sut.createMenu("jacky", 1000, "10g", 2);

        MenuModel actualResult = sut.followUpCreateMenu("jacky");

        System.out.println(firstResult);
        System.out.println(actualResult);
    }
}
