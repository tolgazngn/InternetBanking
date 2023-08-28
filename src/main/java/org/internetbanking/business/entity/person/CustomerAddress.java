package org.internetbanking.business.entity.person;

public class CustomerAddress {
    private String tc;
    private String country;
    private String district;
    private String neighbourhood;
    private String street;
    private String apartmentNo;
    private String doorNo;


    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartment_no(String apartment_no) {
        this.apartmentNo = apartment_no;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoor_no(String door_no) {
        this.doorNo = doorNo;
    }
}
