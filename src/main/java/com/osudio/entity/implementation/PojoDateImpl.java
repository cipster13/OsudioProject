package com.osudio.entity.implementation;

import com.osudio.entity.PojoDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class PojoDateImpl implements PojoDate {

    private LocalDate localDate;
    private DayOfWeek dayOfWeek;
    private LocalTime startingTime;
    private LocalTime closingTime;

    public PojoDateImpl() {
    }

    public PojoDateImpl(LocalDate localDate, LocalTime startingTime, LocalTime closingTime) {
        this.localDate = localDate;
        this.startingTime = startingTime;
        this.closingTime = closingTime;
    }

    public PojoDateImpl(DayOfWeek dayOfWeek, LocalTime startingTime, LocalTime closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.startingTime = startingTime;
        this.closingTime = closingTime;
    }

    @Override
    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
}
