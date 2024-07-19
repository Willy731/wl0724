package com.tool.database;

import com.tool.database.constants.Column;
import com.tool.database.constants.ToolStatus;
//import javax.persistence.Column;
//import javax.persistence.Entity;

public class Tool {
    @Column(name = "toolCode")
    String toolCode ;
    @Column(name = "toolType")
    String toolType ;
    @Column(name = "brand")
    String brand ;
    @Column(name = "rentalStatus")
    String rentalStatus ;
    @Column(name = "conditionStatus")
    String conditionStatus ;
    @Column(name = "rentalTrackingCode")
    String rentalTrackingCode ;


    public Tool() {
    }

    public Tool(String toolCode, String toolType, String brand, String rentalStatus, String conditionStatus, String rentalTrackingCode) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        setRentalStatus(rentalStatus);
        this.conditionStatus = conditionStatus;
        this.rentalTrackingCode = rentalTrackingCode;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ToolStatus getRentalStatus() {
        return ToolStatus.valueOf(rentalStatus);
    }

    @Column(name="rentalStatus")
    public void setRentalStatus(String rentalStatus) {
        try {
            if(ToolStatus.valueOf(rentalStatus).toString().equals(rentalStatus)){
                this.rentalStatus = rentalStatus;
            }
        }catch (Exception e){
            this.rentalStatus = ToolStatus.PENDING_INSPECTION.toString();
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
        return "Tool{" +
                "toolCode='" + toolCode + '\'' +
                ", toolType='" + toolType + '\'' +
                ", brand='" + brand + '\'' +
                ", rentalStatus='" + rentalStatus + '\'' +
                ", conditionStatus='" + conditionStatus + '\'' +
                ", rentalTrackingCode='" + rentalTrackingCode + '\'' +
                '}';
    }

}
