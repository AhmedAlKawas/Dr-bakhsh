package com.example.midok.drbakhsh.Model;

public class LabResults {

    private String date;
    private String doctorName;
    private String description;

    public LabResults(String date, String doctorName, String description) {
        this.date = date;
        this.doctorName = doctorName;
        this.description = description;
    }

    public LabResults() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
