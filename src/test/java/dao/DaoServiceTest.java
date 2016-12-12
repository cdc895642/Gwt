package dao;

import com.mySampleApplication.hibernate.db.tables.User;
import com.mySampleApplication.hibernate.service.UserService;
import com.mySampleApplication.security.PasswordUtils;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by cdc89 on 11.12.2016.
 */
public class DaoServiceTest {
    @Test
    public void findUserTest(){
        UserService userService=new UserService();
        User user=userService.findByLogin("ivan");
        assertEquals("Иван",user.getName());
        String storedPassword=user.getPassword();
        boolean checkPassword= PasswordUtils.checkPassword(user.getName(),"secret",storedPassword);
        assertTrue(checkPassword);
        user=userService.findByLogin("ivan1");
        assertEquals(null,user);
    }
}
