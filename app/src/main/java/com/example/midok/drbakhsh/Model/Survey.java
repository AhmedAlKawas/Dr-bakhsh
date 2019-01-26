package com.example.midok.drbakhsh.Model;

public class Survey {
    private String date;
    private String doctorName;

    public Survey(String date, String doctorName) {
        this.date = date;
        this.doctorName = doctorName;
    }

    public Survey() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
