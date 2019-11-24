package com.osudio.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public interface PojoDate {

    LocalDate getLocalDate();

    void setLocalDate(LocalDate localDate);

    DayOfWeek getDayOfWeek();

    void setDayOfWeek(DayOfWeek daYOfWeek);

    LocalTime getStartingTime();

    void setStartingTime(LocalTime startingtime);

    LocalTime getClosingTime();

    void setClosingTime(LocalTime closingtime);

}
