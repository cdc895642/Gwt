package message;

import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.hibernate.db.tables.User;
import com.mySampleApplication.hibernate.service.UserService;
import com.mySampleApplication.security.PasswordUtils;
import com.mySampleApplication.server.day.Day;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by cdc89 on 13.12.2016.
 */
public class MessageTest {

    @Test
    public void getEnMessageTest(){

    }

    @Test
    public void getRuMessageTest(){

    }

    public String getMessage(String login, String time, String locale) {

        UserService userService = new UserService();
        User user = userService.findByLogin(login);
        if (user == null) {
            return null;
        }
        Day day = getDay(time);
        Locale currentLocal = getLocale(locale);
        String message = getMessage(day, currentLocal);
        return message;
    }

    public Locale getLocale(String locale) {
        Locale currentLocale = new Locale(locale);
        return currentLocale;
    }

    public String getMessage(Day day, Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesResource", locale);
        String message = rb.getString(day.getDayPeriod().toString());
        return message;
    }

    @SuppressWarnings("all")
    public Day getDay(String timeInMillis) {
        Day day = new Day();
        Long longValue = Long.parseLong(timeInMillis);
        Date date = new Date(longValue);
        LocalTime currentTime = LocalTime.of(date.getHours(), date.getMinutes());
        day.setCurrentTime(currentTime);
        return day;
    }
}
