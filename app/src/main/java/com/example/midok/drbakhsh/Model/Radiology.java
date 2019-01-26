package com.example.midok.drbakhsh.Model;

public class Radiology {
    private String docName;
    private String description;
    private String date;

    public Radiology(String docName, String description, String date) {
        this.docName = docName;
        this.description = description;
        this.date = date;
    }

    public Radiology() {
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
