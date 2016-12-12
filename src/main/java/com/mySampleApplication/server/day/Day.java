package com.mySampleApplication.server.day;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;

/**
 * Created by cdc89 on 07.12.2016.
 */
public class Day {
    private static final Logger logger = LogManager.getLogger(Day.class.getName());
    private LocalTime currentTime;

    public Day(){
        currentTime=LocalTime.now();
        logger.debug("currentTime - "+currentTime);
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
        logger.debug("currentTime - "+currentTime);
    }

    public DayPeriod getDayPeriod(){
        LocalTime pm6 = LocalTime.of(6, 0);
        LocalTime pm9 = LocalTime.of(9, 0);
        LocalTime am19 = LocalTime.of(19, 0);
        LocalTime am23 = LocalTime.of(23, 0);
        currentTime.isAfter(pm6);
        DayPeriod dayPeroid = null;
        if ((currentTime.equals(pm6) || currentTime.isAfter(pm6)) && currentTime.isBefore(pm9))
            dayPeroid = DayPeriod.morning;
        if ((currentTime.equals(pm9) || currentTime.isAfter(pm9)) && currentTime.isBefore(am19))
            dayPeroid = DayPeriod.day;
        if ((currentTime.equals(am19) || currentTime.isAfter(am19)) && currentTime.isBefore(am23))
            dayPeroid = DayPeriod.evening;
        if ((currentTime.equals(am23) || currentTime.isAfter(am23)) || currentTime.isBefore(pm6))
            dayPeroid = DayPeriod.night;
        logger.debug("getDayPeriod() - "+dayPeroid);
        return dayPeroid;
    }
}
