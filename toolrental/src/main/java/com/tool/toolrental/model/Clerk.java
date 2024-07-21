package com.tool.toolrental.model;

import com.tool.toolrental.constants.Column;

public class Clerk {
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="streetAddress")
    private String streetAddress;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="zipcode")
    private String zipcode;
    @Column(name="mobilePhoneNumber")
    private String mobilePhoneNumber;
    @Column(name="homePhoneNumber")
    private String homePhoneNumber;
    @Column(name="id")
    private Integer id;

    public Clerk(String firstName, String lastName, String streetAddress, String city, String state, String zipcode, String mobilePhoneNumber, String homePhoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.homePhoneNumber = homePhoneNumber;
    }
    public Clerk(String firstName, String lastName, String streetAddress, String city, String state, String zipcode, String mobilePhoneNumber, String homePhoneNumber, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.id = id;
    }

    public Clerk() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName + ' ' + id;
    }
}
