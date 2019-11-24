package com.osudio.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DryCleaning {
    LocalTime getDefaultOpeningTime();

    void setDefaultOpeningTime(LocalTime defaultOpeningTime);

    LocalTime getDefaultClosingTime();

    void setDefaultClosingTime(LocalTime defaultClosingTime);

    List<PojoDate> getOpeningSpecificDays();

    void setOpeningSpecificDays(List<PojoDate> openingSpecificDays);

    List<PojoDate> getOpeningDays();

    void setOpeningDays(List<PojoDate> openingDays);

    List<LocalDate> getClosingSpecificDays();

    void setClosingSpecificDays(List<LocalDate> closingSpecificDays);

    List<DayOfWeek> getClosingDays();

    void setClosingDays(List<DayOfWeek> closingDays);
}
