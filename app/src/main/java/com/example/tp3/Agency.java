package com.example.tp3;

public class Agency {
    private String name;
    private String address;
    private String responsible;
    private String phone;
    private double latitude;
    private double longitude;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Agency(String name, String address, String responsible, String phone, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.responsible = responsible;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    // getters and setters for other attributes
}
