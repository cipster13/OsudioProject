package com.osudio.entity.implementation;

import com.osudio.entity.DryCleaning;
import com.osudio.entity.PojoDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DryCleaningImpl implements DryCleaning {

    private LocalTime defaultOpeningTime;
    private LocalTime defaultClosingTime;

    List<PojoDate> openingSpecificDays;
    List<PojoDate> openingDays;

    List<LocalDate> closingSpecificDays;
    List<DayOfWeek> closingDays;

    public DryCleaningImpl() {
    }

    public DryCleaningImpl(LocalTime defaultOpeningTime, LocalTime defaultClosingTime) {
        this.defaultOpeningTime = defaultOpeningTime;
        this.defaultClosingTime = defaultClosingTime;
        openingSpecificDays = new ArrayList<>();
        openingDays = new ArrayList<>();
        closingSpecificDays = new ArrayList<>();
        closingDays = new ArrayList<>();
    }

    @Override
    public LocalTime getDefaultOpeningTime() {
        return defaultOpeningTime;
    }

    @Override
    public void setDefaultOpeningTime(LocalTime defaultOpeningTime) {
        this.defaultOpeningTime = defaultOpeningTime;
    }

    @Override
    public LocalTime getDefaultClosingTime() {
        return defaultClosingTime;
    }

    @Override
    public void setDefaultClosingTime(LocalTime defaultClosingTime) {
        this.defaultClosingTime = defaultClosingTime;
    }

    @Override
    public List<PojoDate> getOpeningSpecificDays() {
        return openingSpecificDays;
    }

    @Override
    public void setOpeningSpecificDays(List<PojoDate> openingSpecificDays) {
        this.openingSpecificDays = openingSpecificDays;
    }

    @Override
    public List<PojoDate> getOpeningDays() {
        return openingDays;
    }

    @Override
    public void setOpeningDays(List<PojoDate> openingDays) {
        this.openingDays = openingDays;
    }

    @Override
    public List<LocalDate> getClosingSpecificDays() {
        return closingSpecificDays;
    }

    @Override
    public void setClosingSpecificDays(List<LocalDate> closingSpecificDays) {
        this.closingSpecificDays = closingSpecificDays;
    }

    @Override
    public List<DayOfWeek> getClosingDays() {
        return closingDays;
    }

    @Override
    public void setClosingDays(List<DayOfWeek> closingDays) {
        this.closingDays = closingDays;
    }
}
