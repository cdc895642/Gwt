package time;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * Created by cdc89 on 12.12.2016.
 */
public class TimeTest {
    @Ignore
    @Test
    @SuppressWarnings("all")
    public void getTimeTest(){
        String miliS="1481540858849";
        Long convertLong=Long.parseLong(miliS);
        assertEquals(miliS,convertLong.toString());
        Date date=new Date(convertLong);
        System.out.println(date);
        assertEquals(13,date.getHours());
        assertEquals(7,date.getMinutes());
    }
}
