package com.tool.database;

import com.tool.database.constants.Column;
import com.tool.database.constants.ToolStatus;
import com.tool.database.constants.ToolType;
//import javax.persistence.Column;
//import javax.persistence.Entity;

public class Tool {
    @Column(name = "toolCode")
    String toolCode ;
    @Column(name = "toolType")
    ToolType toolType ;
    @Column(name = "brand")
    String brand ;
    @Column(name = "rentalStatus")
    ToolStatus rentalStatus ;
    @Column(name = "conditionStatus")
    String conditionStatus ;
    @Column(name = "rentalTrackingCode")
    String rentalTrackingCode ;


    public Tool() {
    }

    public Tool(String toolCode, ToolType toolType, String brand, ToolStatus rentalStatus, String conditionStatus, String rentalTrackingCode) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.rentalStatus = rentalStatus;
        this.conditionStatus = conditionStatus;
        this.rentalTrackingCode = rentalTrackingCode;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ToolStatus getRentalStatus() {
        return rentalStatus;
    }

    @Column(name="rentalStatus")
    public void setRentalStatus(ToolStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
    @Column(name="rentalStatus")
    public void setRentalStatus(String rentalStatus) {
        try {
            if(ToolStatus.valueOf(rentalStatus).toString().equals(rentalStatus)){
                this.rentalStatus = ToolStatus.valueOf(rentalStatus);
            }
        }catch (Exception e){
            this.rentalStatus = ToolStatus.PENDING_INSPECTION;
        }
    }

    public String getConditionStatus() {
        return conditionStatus;
    }

    public void setConditionStatus(String conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    public String getRentalTrackingCode() {
        return rentalTrackingCode;
    }

    public void setRentalTrackingCode(String rentalTrackingCode) {
        this.rentalTrackingCode = rentalTrackingCode;
    }

    @Override
    public String toString() {
        return toolType.toString() + ' ' +
                ", " + brand + '\'' +
                ", " + conditionStatus + '\'';
    }

}
