package day;

import com.mySampleApplication.server.day.Day;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalTime;

/**
 * Created by cdc89 on 07.12.2016.
 */
public class DayTest {
    Day day = new Day();

    @Ignore
    @Test
    public void testCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);
        LocalTime pm6 = LocalTime.of(6, 0);
        LocalTime pm9 = LocalTime.of(9, 0);
        LocalTime am19 = LocalTime.of(19, 0);
        LocalTime am23 = LocalTime.of(23, 0);
        currentTime.isAfter(pm6);
        String dayPeroid = null;
        if ((currentTime.equals(pm6) || currentTime.isAfter(pm6)) && currentTime.isBefore(pm9))
            dayPeroid = "morning";
        if ((currentTime.equals(pm9) || currentTime.isAfter(pm9)) && currentTime.isBefore(am19))
            dayPeroid = "day";
        if ((currentTime.equals(am19) || currentTime.isAfter(am19)) && currentTime.isBefore(am23))
            dayPeroid = "evening";
        if ((currentTime.equals(am23) || currentTime.isAfter(am23)) || currentTime.isBefore(pm6))
            dayPeroid = "night";
        System.out.println(dayPeroid);
    }

    @Test
    public void morningTest() {
        day.setCurrentTime(LocalTime.of(6, 0));
        assertEquals(day.getDayPeriod().toString(), "morning");
        day.setCurrentTime(LocalTime.of(7, 0));
        assertEquals(day.getDayPeriod().toString(), "morning");
        day.setCurrentTime(LocalTime.of(9, 0));
        assertNotEquals(day.getDayPeriod().toString(), "morning");
        day.setCurrentTime(LocalTime.of(10, 0));
        assertNotEquals(day.getDayPeriod().toString(), "morning");
        day.setCurrentTime(LocalTime.of(5, 0));
        assertNotEquals(day.getDayPeriod().toString(), "morning");
    }

    @Test
    public void dayTest() {
        day.setCurrentTime(LocalTime.of(9, 0));
        assertEquals(day.getDayPeriod().toString(), "day");
        day.setCurrentTime(LocalTime.of(10, 0));
        assertEquals(day.getDayPeriod().toString(), "day");
        day.setCurrentTime(LocalTime.of(19, 0));
        assertNotEquals(day.getDayPeriod().toString(), "day");
        day.setCurrentTime(LocalTime.of(20, 0));
        assertNotEquals(day.getDayPeriod().toString(), "day");
        day.setCurrentTime(LocalTime.of(5, 0));
        assertNotEquals(day.getDayPeriod().toString(), "day");
    }

    @Test
    public void eveningTest() {
        day.setCurrentTime(LocalTime.of(19, 0));
        assertEquals(day.getDayPeriod().toString(), "evening");
        day.setCurrentTime(LocalTime.of(20, 0));
        assertEquals(day.getDayPeriod().toString(), "evening");
        day.setCurrentTime(LocalTime.of(23, 0));
        assertNotEquals(day.getDayPeriod().toString(), "evening");
        day.setCurrentTime(LocalTime.of(23, 30));
        assertNotEquals(day.getDayPeriod().toString(), "evening");
        day.setCurrentTime(LocalTime.of(5, 0));
        assertNotEquals(day.getDayPeriod().toString(), "evening");
    }

    @Test
    public void nightTest() {
        day.setCurrentTime(LocalTime.of(23, 0));
        assertEquals(day.getDayPeriod().toString(), "night");
        day.setCurrentTime(LocalTime.of(23, 30));
        assertEquals(day.getDayPeriod().toString(), "night");
        day.setCurrentTime(LocalTime.of(0, 0));
        assertEquals(day.getDayPeriod().toString(), "night");
        day.setCurrentTime(LocalTime.of(6, 00));
        assertNotEquals(day.getDayPeriod().toString(), "night");
        day.setCurrentTime(LocalTime.of(22, 0));
        assertNotEquals(day.getDayPeriod().toString(), "night");
    }
}
