package com.tool.toolrental.agreements;

import java.time.LocalDate;

public class RentalAgreement {
    private String renterName;
    private String renterContactNumber;
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private String preDiscountCharge;
    private double discountPercent;
    private String discountAmount;
    private String finalCharge;
    private LocalDate date;
    private String clerkIdNumber;

    public RentalAgreement() {
    }

    public RentalAgreement(String renterName, String renterContactNumber, String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkOutDate, LocalDate dueDate, double dailyRentalCharge, int chargeDays, String preDiscountCharge, double discountPercent, String discountAmount, String finalCharge, LocalDate date, String clerkIdNumber) {
        this.renterName = renterName;
        this.renterContactNumber = renterContactNumber;
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
        this.date = date;
        this.clerkIdNumber = clerkIdNumber;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterContactNumber() {
        return renterContactNumber;
    }

    public void setRenterContactNumber(String renterContactNumber) {
        this.renterContactNumber = renterContactNumber;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(String preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(String finalCharge) {
        this.finalCharge = finalCharge;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClerkIdNumber() {
        return clerkIdNumber;
    }

    public void setClerkIdNumber(String clerkIdNumber) {
        this.clerkIdNumber = clerkIdNumber;
    }
}