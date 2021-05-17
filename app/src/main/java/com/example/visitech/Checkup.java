package com.example.visitech;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Checkup {
    private Calendar date;
    private Doctor doctor;
    private String description;
    private String findings;

    public Checkup(Calendar date, Doctor doctor, String description, String findings){
        this.date = date;
        this.description = description;
        this.doctor = doctor;
        this.findings = findings;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public Calendar getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDescription() {
        return description;
    }

    public String getFindings() {
        return findings;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = formatter.format(this.getDate().getTime());
        return String.format("Done by %s on %s", this.getDoctor(), strDate);
    }
}
