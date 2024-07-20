package com.tool.database;

import com.tool.database.constants.Column;
import com.tool.database.constants.ToolType;

public class Charge {
    /*
    toolType
    level
    dailyCharge
    weekdayCharge
    weekendCharge
    holidayCharge
    baseCharge
     */

    @Column(name="toolType")
    ToolType toolType;
    @Column(name="level")
    String level;
    @Column(name="dailyCharge")
    String dailyCharge;
    @Column(name="weekdayCharge")
    boolean weekdayCharge;
    @Column(name="weekendCharge")
    boolean weekendCharge;
    @Column(name="holidayCharge")
    boolean holidayCharge;
    @Column(name="baseCharge")
    String baseCharge;

    public Charge() {
    }

    public Charge(ToolType toolType, String level, String dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge, String baseCharge) {
        this.toolType = toolType;
        this.level = level;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
        this.baseCharge = baseCharge;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }
    public void setToolType(String toolType) {
        this.toolType = ToolType.valueOf(toolType);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(String dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }
}
