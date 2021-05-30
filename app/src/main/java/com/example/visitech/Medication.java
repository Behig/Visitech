package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Medication implements Parcelable {
    private String name;
    private double dose;
    private Day weekDay;

    public Medication(String name, double dose, Day weekDay){
        this.name = name;
        this.dose = dose;
        this.weekDay = weekDay;
    }

    protected Medication(Parcel in) {
        name = in.readString();
        dose = in.readDouble();
        weekDay = Day.valueOf(in.readString());
    }

    public static final Creator<Medication> CREATOR = new Creator<Medication>() {
        @Override
        public Medication createFromParcel(Parcel in) {
            return new Medication(in);
        }

        @Override
        public Medication[] newArray(int size) {
            return new Medication[size];
        }
    };

    public Day getWeekDay() {
        return weekDay;
    }

    public double getDose() {
        return dose;
    }

    public String getName() {
        return name;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeekDay(Day weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString(){
        return String.format("%f dose of %s", this.getDose(), this.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(dose);
        dest.writeString(this.getWeekDay().name());
    }
}
