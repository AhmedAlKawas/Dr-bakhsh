package com.example.midok.drbakhsh.Model;

public class Appointments {
    private String doctorName;
    private String decription;
    private String date;

    public Appointments() {
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Appointments(String doctorName, String decription, String date) {
        this.doctorName = doctorName;
        this.decription = decription;
        this.date = date;
    }
}
