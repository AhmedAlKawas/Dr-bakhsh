package com.example.midok.drbakhsh.Model;

public class Complaints {
    private String involvedPersonName;
    private String date;
    private String status;

    public Complaints(String involvedPersonName, String date, String status) {
        this.involvedPersonName = involvedPersonName;
        this.date = date;
        this.status = status;
    }

    public Complaints() {
    }

    public String getInvolvedPersonName() {
        return involvedPersonName;
    }

    public void setInvolvedPersonName(String involvedPersonName) {
        this.involvedPersonName = involvedPersonName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
