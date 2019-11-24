package com.osudio.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface BusinessHourCalculator {

    void setOpeningHours(DayOfWeek day, String openingHour, String closingHour) throws Exception;

    void setOpeningHours(String day, String openingHour, String closingHour) throws Exception;

    void setClosed(List<DayOfWeek> days);

    void setClosed(String [] days);

    Date calculateDeadline(long seconds, LocalDate day, LocalTime hour);
}
