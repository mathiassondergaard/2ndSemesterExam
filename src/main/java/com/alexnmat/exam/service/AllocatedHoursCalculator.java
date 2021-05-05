package com.alexnmat.exam.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class AllocatedHoursCalculator {

    //from STACKO (find link)
    public long calculateTotalWorkdayHours(LocalDate startDate, LocalDate endDate) {
        //Get startDay's value and endDay's value
        final int startDay = startDate.getDayOfWeek().getValue();
        final int endDay = endDate.getDayOfWeek().getValue();

        //Get days between start and end date
        final long days = ChronoUnit.DAYS.between(startDate, endDate);
        //Removes weekends from days
        long result = days - 2*(days/7);

        //Deal with the rest of the days
        if (days % 7 != 0) {
            if (startDay == 7) {
                result -= 1;
            } else if (endDay == 7) { //both days cant be sunday, would mean result == 0
                result -= 1;
            } else if (endDay < startDay) { //another weekend included.
                result -= 2;
            }
        }
        //One workday equals 8 hours
        int workdayHours = 8;

        return result * workdayHours;
    }
}
