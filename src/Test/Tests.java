package Test;
import Controller.UserInterface.FriendsViewController;
import Controller.UserInterface.GroupsViewController;
import Controller.UserInterface.SearchViewController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests
{
    @Test
    public void testDataColumnsFriendsView()
    {
        FriendsViewController controller = new FriendsViewController();
        assertNotEquals(null,controller.getDataColumns()); // data columns null gelip gelmediğini kontrol eder;
    }

    @Test
    public void testSearchGetDataColumns() {
        SearchViewController controller  =new SearchViewController();
        assertNotEquals(null, controller.GetAllUsers());
    }

    @Test
    public void testGroupDataColumns() { // data columnsun null gelip gelmediğini kontrol eder
        GroupsViewController controller = new GroupsViewController();
        assertNotEquals(null,controller.getDataColumns());
    }

}
