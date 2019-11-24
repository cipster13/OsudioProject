package com.osudio.service.implementation;

import com.osudio.entity.DryCleaning;
import com.osudio.entity.PojoDate;
import com.osudio.entity.implementation.DryCleaningImpl;
import com.osudio.entity.implementation.PojoDateImpl;
import com.osudio.service.BusinessHourCalculator;

import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BusinessHourCalculatorImpl implements BusinessHourCalculator {

    private DryCleaning dryCleaning;
    private static final String errorString = "Somethings wrong with the input data format. Hours should be formatted as hh:mm and dates as yyyy-MM-dd";

    public BusinessHourCalculatorImpl(String defaultOpeningTime, String defaultClosingTime) throws Exception {
        try{

            String[] splitDefaultOpeningTime = defaultOpeningTime.split(":");
            LocalTime openingTime = LocalTime.of(Integer.parseInt(splitDefaultOpeningTime[0]), Integer.parseInt(splitDefaultOpeningTime[1]));
            String[] splitClosingTime = defaultClosingTime.split(":");
            LocalTime closingTime = LocalTime.of(Integer.parseInt(splitClosingTime[0]), Integer.parseInt(splitClosingTime[1]));
            dryCleaning = new DryCleaningImpl(openingTime, closingTime);

        }catch (Exception e){
            throw new Exception(errorString);
        }
    }


    @Override
    public void setOpeningHours(DayOfWeek day, String openingHour, String closingHour) throws Exception {
        try {
            String[] splitOpeningHour = openingHour.split(":");
            LocalTime openingTime = LocalTime.of(Integer.parseInt(splitOpeningHour[0]), Integer.parseInt(splitOpeningHour[1]));
            String[] splitClosingHour = closingHour.split(":");
            LocalTime closingTime = LocalTime.of(Integer.parseInt(splitClosingHour[0]), Integer.parseInt(splitClosingHour[1]));
            dryCleaning.getOpeningDays().add(new PojoDateImpl(day, openingTime, closingTime));
        }catch (Exception e){
            throw new Exception(errorString);
        }
    }

    @Override
    public void setOpeningHours(String day, String openingHour, String closingHour) throws Exception {

        try {
            LocalDate date = LocalDate.parse(day);
            String[] splitOpeningHour = openingHour.split(":");
            LocalTime openingTime = LocalTime.of(Integer.parseInt(splitOpeningHour[0]), Integer.parseInt(splitOpeningHour[1]));
            String[] splitClosingHour = closingHour.split(":");
            LocalTime closingTime = LocalTime.of(Integer.parseInt(splitClosingHour[0]), Integer.parseInt(splitClosingHour[1]));
            dryCleaning.getOpeningSpecificDays().add(new PojoDateImpl(date, openingTime, closingTime));
        }catch (Exception e){
            throw new Exception(errorString);
        }


    }

    @Override
    public void setClosed(List<DayOfWeek> days) {
        days.stream().forEach(dryCleaning.getClosingDays()::add);
    }

    @Override
    public void setClosed(String[] days) {
        try {
            Arrays.stream(days).forEach(d ->
                    dryCleaning.getClosingSpecificDays().add(LocalDate.parse(d))
            );
        }catch (Exception e){
            throw  e;
        }
    }

    @Override
    public Date calculateDeadline(long seconds, LocalDate day, LocalTime arrivingHour) {

        PojoDate validDate = getValidDate(day);

        //If it arrives before, i'll redo the calculate, by setting the arriving hour to the opening dryCleaner one
        if (arrivingHour.isBefore(validDate.getStartingTime())) {
            return calculateDeadline(seconds, day, validDate.getStartingTime());
        }

        //If it arrives after the closing, i'll redo the calculate by adding 1 day and setting it's opening hour as the arriving hour
        if (arrivingHour.isAfter(validDate.getClosingTime())) {
            validDate = getValidDate(day.plusDays(1));
            return calculateDeadline(seconds, validDate.getLocalDate(), validDate.getStartingTime());
        }

        //If everything is ok, i calculate the time for the cleaning
        LocalTime arrivingHourPlusSeconds = arrivingHour.plusSeconds(seconds);
        if (arrivingHourPlusSeconds.isAfter(validDate.getClosingTime())) {
            //If the cleaning take more than the dryCleaner closing time, i redo the calculate by finding the next
            //vale day and starting from it's opening hour
            Duration between = Duration.between(validDate.getClosingTime(), arrivingHourPlusSeconds);
            validDate = getValidDate(day.plusDays(1));
            return calculateDeadline(between.getSeconds(), validDate.getLocalDate(), validDate.getStartingTime());
        }else{
            //If we are here, it means the the date has been fount and could be correctly returned
            return Date.from( LocalDateTime.of(validDate.getLocalDate(), arrivingHourPlusSeconds).atZone( ZoneId.systemDefault()).toInstant());
        }

    }





    private PojoDate getValidDate(LocalDate day) {
        boolean fountDay = false;
        //Check if the input day is available for the dryCleaner
        while (!fountDay){
            if (day.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                    day.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                        dryCleaning.getClosingDays().contains(day.getDayOfWeek()) ||
                            dryCleaning.getClosingSpecificDays().contains(day)) {
                //add a day and redo the check, till the available day is fount
                day = day.plusDays(1);
            }else {
                fountDay = true;
            }
        }
        final LocalDate firtCheckedDay = day;
        PojoDate validDate = null;
        //Check if the date has precise time configuration
        Optional<PojoDate> firstOSD = dryCleaning.getOpeningSpecificDays().stream().filter(pd -> pd.getLocalDate().equals(firtCheckedDay)).findFirst();
        if (firstOSD.isPresent()) {
            validDate = firstOSD.get();
        }else{
            //If not, i check if there are some configuration for that day of the week
            Optional<PojoDate> firstOD = dryCleaning.getOpeningDays().stream().filter(pd -> pd.getDayOfWeek().equals(firtCheckedDay.getDayOfWeek())).findFirst();
            if (firstOD.isPresent()) {
                validDate = firstOD.get();
            }else {
                //If not, means that it is a normal day with normal opening/closing time
                validDate = new PojoDateImpl(day, dryCleaning.getDefaultOpeningTime(), dryCleaning.getDefaultClosingTime());
            }
        }
        return validDate;
    }

}
