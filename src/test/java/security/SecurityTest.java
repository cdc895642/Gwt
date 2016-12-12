package security;

import com.mySampleApplication.security.PasswordUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by cdc89 on 11.12.2016.
 */
public class SecurityTest {
    @Test
    public void checkPasswordTest(){
        String userName="Иван";
        String password="secret";
        String forSalt="Random_Salt#Value@SPEC|SYMBOLS!@#$%^";
        String storedPassword=getHashPassword(userName, password, forSalt);
        boolean result= PasswordUtils.checkPassword(userName,password,storedPassword);
        assertTrue(result);
        result= PasswordUtils.checkPassword(userName+" ",password,storedPassword);
        assertFalse(result);
        result= PasswordUtils.checkPassword(userName,password+" ",storedPassword);
        assertFalse(result);
        result= PasswordUtils.checkPassword(userName,password,storedPassword+" ");
        assertFalse(result);
        result= PasswordUtils.checkPassword(userName+" ",password+" ",storedPassword+" ");
        assertFalse(result);
    }

    private String getHashPassword(String userName, String password, String saltPart){
        String p=password+userName+saltPart;
        String md5Hex = DigestUtils.md5Hex(p);
        return md5Hex;
    }

}
