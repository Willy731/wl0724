package com.tool.toolrental.agreements;

import com.tool.toolrental.constants.Column;
import com.tool.toolrental.dao.ChargeDB;
import com.tool.toolrental.model.*;
import com.tool.toolrental.utils.PaymentUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalAgreement {
    @Column(name="renterName")          private String renterName           ;
    @Column(name="renterContactNumber") private String renterContactNumber  ;
    @Column(name="toolCode")            private String toolCode             ;
    @Column(name="toolType")            private String toolType             ;
    @Column(name="toolBrand")           private String toolBrand            ;
    @Column(name="rentalDays")          private int rentalDays              = 0;
    @Column(name="checkOutDate")        private LocalDate checkOutDate      = LocalDate.now();
    @Column(name="dueDate")             private LocalDate dueDate           = LocalDate.now().plusDays(1);
    @Column(name="dailyRentalCharge")   private double dailyRentalCharge    = 0;
    @Column(name="chargeDays")          private int chargeDays              = 1;
    @Column(name="preDiscountCharge")   private String preDiscountCharge    ;
    @Column(name="discountPercent")     private int discountPercent         = 0;
    @Column(name="discountAmount")      private String discountAmount       ;
    @Column(name="finalCharge")         private String finalCharge          ;
    @Column(name="date")                private LocalDate date              = LocalDate.now();
    @Column(name="clerkIdNumber")       private String clerkIdNumber        ;



    private Renter renter = null;
    private Clerk clerk = null;
    private Tool selectedTool;
    private ChargeDB chargeDB;
    private List<Charge> charges;
    private Charge chargeObj;

    public RentalAgreement() {
        chargeDB = new ChargeDB();
        charges = chargeDB.getChargesList();

    }

    public RentalAgreement(String renterName, String renterContactNumber, String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkOutDate, LocalDate dueDate, double dailyRentalCharge, int chargeDays, String preDiscountCharge, int discountPercent, String discountAmount, String finalCharge, LocalDate date, String clerkIdNumber) {
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


        chargeDB = new ChargeDB();
        charges = chargeDB.getChargesList();

    }

    private void updateChargeValues(){
        if(selectedTool!=null && checkOutDate!=null){

            PaymentUtils paymentUtils = new PaymentUtils();

            dueDate = checkOutDate.plusDays(rentalDays);

            chargeDays = paymentUtils.countChargeDays(checkOutDate, dueDate, chargeObj);
            preDiscountCharge = paymentUtils.format(paymentUtils.calculateRate(chargeObj, chargeDays));
            discountAmount = paymentUtils.format(paymentUtils.calculateDiscount(chargeObj, chargeDays,discountPercent));
            finalCharge = paymentUtils.format(paymentUtils.calculateAmountOwed(chargeObj, chargeDays,discountPercent));
        }
    }


    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
        renterName = renter.getName();
        renterContactNumber = renter.getMobilePhoneNumber();
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
        clerkIdNumber = clerk.getId().toString();
    }

    public Tool getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;
        toolBrand = selectedTool.getBrand();
        toolCode = selectedTool.getToolCode();
        toolType = selectedTool.getToolType().toString();
        //Get Charge row
        Optional<Charge> toolCharge = charges.stream()
                .filter(charge -> (charge.getToolType().equals(selectedTool.getToolType())
                        && charge.getLevel().equals("1"))).findFirst();
        if(toolCharge.isPresent()){
            chargeObj = toolCharge.get();
            dailyRentalCharge = Double.parseDouble(chargeObj.getDailyCharge());
            updateChargeValues();
        }else{
            return;
        }
    }

    public Charge getChargeObj() {
        return chargeObj;
    }

    public void setChargeObj(Charge chargeObj) {
        this.chargeObj = chargeObj;
    }


    /*
     *  Boiler getters and setters
     *
     */

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
        updateChargeValues();
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
        updateChargeValues();
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

    public void setDiscountPercent(int discountPercent) {
        if(discountPercent > 100){
            discountPercent = 100;
        }
        if(discountPercent < 0){
            discountPercent = 0;
        }
        this.discountPercent = discountPercent;
        updateChargeValues();
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