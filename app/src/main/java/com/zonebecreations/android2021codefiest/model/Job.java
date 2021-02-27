package com.zonebecreations.android2021codefiest.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class Job {
    String jobDocId;
    String DriverDocId;
    double startLocationLat;
    double startLocationLan;
    double endLocationLat;
    double endLocationLan;
    String customerName;
    Date jobCreatedAt;
    double estimatedPrice;
    String durationString;
    String mobile;
    String email;
    String status;
    Date statusTime;

    double driverCurrentLat;
    double driverCurrentLan;

    double customerCurrentLat;
    double customerCurrentLan;

    public Job() {
    }

    public Job(double startLocationLat, double startLocationLan, double endLocationLat, double endLocationLan, String customerName, Date jobCreatedAt, double estimatedPrice, String durationString, String mobile, String email, String status, Date statusTime) {
        this.startLocationLat = startLocationLat;
        this.startLocationLan = startLocationLan;
        this.endLocationLat = endLocationLat;
        this.endLocationLan = endLocationLan;
        this.customerName = customerName;
        this.jobCreatedAt = jobCreatedAt;
        this.estimatedPrice = estimatedPrice;
        this.durationString = durationString;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
        this.statusTime = statusTime;
    }

    public double getCustomerCurrentLat() {
        return customerCurrentLat;
    }

    public void setCustomerCurrentLat(double customerCurrentLat) {
        this.customerCurrentLat = customerCurrentLat;
    }

    public double getCustomerCurrentLan() {
        return customerCurrentLan;
    }

    public void setCustomerCurrentLan(double customerCurrentLan) {
        this.customerCurrentLan = customerCurrentLan;
    }

    public double getDriverCurrentLat() {
        return driverCurrentLat;
    }

    public void setDriverCurrentLat(double driverCurrentLat) {
        this.driverCurrentLat = driverCurrentLat;
    }

    public double getDriverCurrentLan() {
        return driverCurrentLan;
    }

    public void setDriverCurrentLan(double driverCurrentLan) {
        this.driverCurrentLan = driverCurrentLan;
    }

    public String getDriverDocId() {
        return DriverDocId;
    }

    public void setDriverDocId(String driverDocId) {
        DriverDocId = driverDocId;
    }

    public String getJobDocId() {
        return jobDocId;
    }

    public void setJobDocId(String jobDocId) {
        this.jobDocId = jobDocId;
    }

    public double getStartLocationLat() {
        return startLocationLat;
    }

    public void setStartLocationLat(double startLocationLat) {
        this.startLocationLat = startLocationLat;
    }

    public double getStartLocationLan() {
        return startLocationLan;
    }

    public void setStartLocationLan(double startLocationLan) {
        this.startLocationLan = startLocationLan;
    }

    public double getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(double endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public double getEndLocationLan() {
        return endLocationLan;
    }

    public void setEndLocationLan(double endLocationLan) {
        this.endLocationLan = endLocationLan;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getJobCreatedAt() {
        return jobCreatedAt;
    }

    public void setJobCreatedAt(Date jobCreatedAt) {
        this.jobCreatedAt = jobCreatedAt;
    }

    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }
}
