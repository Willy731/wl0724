package com.tool.toolrental.model;

import com.tool.toolrental.constants.Column;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class HolidayCalendar {
    /*
    holidayName
    observedDate
     */
    @Column(name="holidayName")
    String holidayName;
    @Column(name="observedDate")
    LocalDate observedDate;

    public HolidayCalendar() {
    }

    public HolidayCalendar(String holidayName, LocalDate observedDate) {
        this.holidayName = holidayName;
        this.observedDate = observedDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public LocalDate getObservedDate() {
        return observedDate;
    }

    public void setObservedDate(LocalDate observedDate) {
        this.observedDate = observedDate;
    }
    public void setObservedDate(Date observedDate) {
        this.observedDate = observedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
