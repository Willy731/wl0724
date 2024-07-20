package com.tool.utils;

import com.tool.database.Charge;
import com.tool.database.HolidayCalendarDB;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;

public class PaymentUtils {

    private Currency currency = Currency.getInstance("USD");

    public BigDecimal calculateRate(Charge charge, int chargeDays){
        BigDecimal base = new BigDecimal(charge.getBaseCharge());
        BigDecimal dailyRate = new BigDecimal(charge.getDailyCharge());
        BigDecimal preDiscount = base.add(dailyRate.multiply(new BigDecimal(chargeDays)));
        return preDiscount;
    }
    public BigDecimal calculateDiscount(Charge charge, int chargeDays, int discount){
        BigDecimal base = new BigDecimal(charge.getBaseCharge());
        BigDecimal dailyRate = new BigDecimal(charge.getDailyCharge());
        BigDecimal preDiscount = base.add(dailyRate.multiply(new BigDecimal(chargeDays)));
        return preDiscount.multiply(new BigDecimal(discount/100.0));
    }
    public BigDecimal calculateAmountOwed(Charge charge, int chargeDays, int discount){
        BigDecimal base = new BigDecimal(charge.getBaseCharge());
        BigDecimal dailyRate = new BigDecimal(charge.getDailyCharge());
        BigDecimal preDiscount = base.add(dailyRate.multiply(new BigDecimal(chargeDays)));
        return preDiscount.multiply(new BigDecimal((100.0-discount)/100.0));
    }

    public BigDecimal applyPercentage(BigDecimal amount, BigDecimal percentage) {
        return amount.multiply(percentage).divide(BigDecimal.valueOf(100), currency.getDefaultFractionDigits(), BigDecimal.ROUND_HALF_UP);
    }

    public String format(BigDecimal amount) {
        return String.format("%s %,.2f", currency.getSymbol(), amount);
    }

    public int countChargeDays(LocalDate startDate, LocalDate endDate, Charge chargeRecord) {
        int totalchargeDays = 0;
        try {
            if (chargeRecord.getWeekdayCharge()) {
                totalchargeDays += countWeekdays(startDate, endDate);
            }
            if (chargeRecord.getWeekendCharge()) {
                totalchargeDays += countWeekendDays(startDate, endDate);
            }
            if (!chargeRecord.getHolidayCharge()) {
                totalchargeDays -= observedHolidays(startDate, endDate);
            }

            return totalchargeDays;
        }catch (Exception e) {
            return 0;
        }
    }

    private static int observedHolidays(LocalDate startDate, LocalDate endDate) {
        HolidayCalendarDB holidayDB = new HolidayCalendarDB();
        return holidayDB.getHolidayCalendarsList(startDate, endDate).size();
    }

    public static int countWeekendDays(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }

        int weekendDaysCount = 0;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekendDaysCount++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return weekendDaysCount;
    }

    public static int countWeekdays(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }

        int weekdaysCount = 0;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                weekdaysCount++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return weekdaysCount;
    }

}
