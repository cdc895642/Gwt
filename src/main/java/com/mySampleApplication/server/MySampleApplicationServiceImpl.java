package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.hibernate.db.tables.User;
import com.mySampleApplication.hibernate.service.UserService;
import com.mySampleApplication.security.PasswordUtils;
import com.mySampleApplication.server.day.Day;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    private static final Logger logger = LogManager.getLogger(MySampleApplicationServiceImpl.class.getName());

    public Result getMessage(String login, String password, String time, String locale) {

        UserService userService = new UserService();
        User user = userService.findByLogin(login);
        if (user == null) {
            logger.info("server return - user does not exist");
        }
        String storedPassword = user.getPassword();
        boolean result = PasswordUtils.checkPassword(user.getName(), password, storedPassword);
        Result res = new Result();
        Day day = getDay(time);
        Locale currentLocal = getLocale(locale);
        String message = getMessage(day, currentLocal);
        if (result) {
            res.answerText = message+" "+user.getName();
            res.linkText = "Выйти из системы";
        } else {
            res.answerText = "wrong login or password";
            res.linkText = "back";
        }
        logger.info("server return - " + res.answerText);
        return res;
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

    public Locale getLocale(String locale) {
        Locale currentLocale = new Locale(locale);
        return currentLocale;
    }

    public String getMessage(Day day, Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesResource", locale);
        String message = rb.getString(day.getDayPeriod().toString());
        logger.debug("getMessage() - locale - " + locale + "; DayPeriod - " + day.getDayPeriod() + "; message - " + message);
        return message;
    }
}