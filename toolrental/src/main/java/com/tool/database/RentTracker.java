package com.tool.database;

import com.tool.database.constants.Column;

public class RentTracker {
    /**
     * id
     * toolId
     * renterId
     * clerkId
     * rentalDays
     * checkoutDate
     * chargeDays
     * preDiscountCharge
     * discountPercent
     * discountAmount
     * finalCharge
     */
    @Column(name="id")
    Integer id;
    @Column(name="toolId")
    String toolId;
    @Column(name="renterId")
    Integer renterId;
    @Column(name="clerkId")
    Integer clerkId;
    @Column(name="rentalDays")
    String rentalDays;
    @Column(name="checkoutDate")
    String checkoutDate;
    @Column(name="chargeDays")
    String chargeDays;
    @Column(name="preDiscountCharge")
    String preDiscountCharge;
    @Column(name="discountPercent")
    String discountPercent;
    @Column(name="discountAmount")
    String discountAmount;
    @Column(name="finalCharge")
    String finalCharge;

    public RentTracker() {

    }

    public RentTracker(Integer id, String toolId, Integer renterId, Integer clerkId, String rentalDays, String checkoutDate, String chargeDays, String preDiscountCharge, String discountPercent, String discountAmount, String finalCharge) {
        this.id = id;
        this.toolId = toolId;
        this.renterId = renterId;
        this.clerkId = clerkId;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public RentTracker(String toolId, Integer renterId, Integer clerkId, String rentalDays, String checkoutDate, String chargeDays, String preDiscountCharge, String discountPercent, String discountAmount, String finalCharge) {
        this.toolId = toolId;
        this.renterId = renterId;
        this.clerkId = clerkId;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }
    public void setRenterId(String renterId) {
        this.renterId = Integer.parseInt(renterId);
    }

    public Integer getClerkId() {
        return clerkId;
    }

    public void setClerkId(Integer clerkId) {
        this.clerkId = clerkId;
    }
    public void setClerkId(String clerkId) {
        this.clerkId = Integer.parseInt(clerkId);
    }

    public String getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(String rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(String chargeDays) {
        this.chargeDays = chargeDays;
    }

    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(String preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
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
}
