package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Checkup implements Parcelable {
    private String[] date;
    private Doctor doctor;
    private String areas;
    private String findings;

    public Checkup(String[] date, Doctor doctor, String areas, String findings){
        this.date = date;
        this.areas = areas;
        this.doctor = doctor;
        this.findings = findings;
    }

    protected Checkup(Parcel in) {
        date = in.createStringArray();
        doctor = in.readParcelable(Doctor.class.getClassLoader());
        areas = in.readString();
        findings = in.readString();
    }

    public static final Creator<Checkup> CREATOR = new Creator<Checkup>() {
        @Override
        public Checkup createFromParcel(Parcel in) {
            return new Checkup(in);
        }

        @Override
        public Checkup[] newArray(int size) {
            return new Checkup[size];
        }
    };

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String[] getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getAreas() {
        return areas;
    }

    public String getFindings() {
        return findings;
    }

    @Override
    public String toString() {
        return String.format("Done by \"%s\" on %s.%s.%s", this.getDoctor(), this.getDate()[2], this.getDate()[1], this.getDate()[0]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(date);
        dest.writeParcelable(doctor, flags);
        dest.writeString(areas);
        dest.writeString(findings);
    }
}
