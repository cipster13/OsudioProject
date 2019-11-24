package com.osudio;

import com.osudio.service.BusinessHourCalculator;
import com.osudio.service.implementation.BusinessHourCalculatorImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {
        BusinessHourCalculator businessHourCalculator = new BusinessHourCalculatorImpl("09:00", "15:00");
        businessHourCalculator.setOpeningHours(DayOfWeek.FRIDAY, "10:00", "17:00");
        businessHourCalculator.setOpeningHours("2010-12-24", "8:00", "13:00");
        List<DayOfWeek> l = new ArrayList<>();
        l.add(DayOfWeek.SUNDAY);
        l.add(DayOfWeek.WEDNESDAY);
        businessHourCalculator.setClosed(l);
        businessHourCalculator.setClosed(new String[]{"2010-12-25"});

        System.out.println(businessHourCalculator.calculateDeadline(2*60*60, LocalDate.parse("2010-06-07"), LocalTime.parse("09:10")));

        System.out.println(businessHourCalculator.calculateDeadline(15*60, LocalDate.parse("2010-06-08"), LocalTime.parse("14:48")));

        System.out.println(businessHourCalculator.calculateDeadline(7*60*60, LocalDate.parse("2010-12-24"), LocalTime.parse("06:45")));
    }
}
