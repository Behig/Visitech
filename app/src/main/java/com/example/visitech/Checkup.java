package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A checkup.
 *
 * This is a class representing a checkup, which contains details like the date of checkup, the doctor doing
 * the checkup, areas of patient's body, where is being checked, and the findings of checkup.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class Checkup implements Parcelable {
    /**
     * Year is the index 0, month is index 1 and day is index 2.
     */
    private String[] date;
    private Doctor doctor;
    private String areas;
    private String findings;

    /**
     * Custom constructor of class.
     *
     * @param date Date of the checkup.
     * @param doctor The doctor, who is responsible for the checkup.
     * @param areas Areas of patient's body, being checked.
     * @param findings Findings during the checkup.
     */
    public Checkup(String[] date, Doctor doctor, String areas, String findings){
        this.date = date;
        this.areas = areas;
        this.doctor = doctor;
        this.findings = findings;
    }

    /**
     *The constructor for making an instance from a parcelable object.
     *
     * @param in The parcelable object, which contains the saved instance.
     */
    protected Checkup(Parcel in) {
        date = in.createStringArray();
        doctor = in.readParcelable(Doctor.class.getClassLoader());
        areas = in.readString();
        findings = in.readString();
    }

    /**
     * The Creator of a parcelable object for the class Checkup, which contains useful methods to
     * make instance of class Checkup and also an array of the instances of the class Checkup.
     */
    public static final Creator<Checkup> CREATOR = new Creator<Checkup>() {

        /**
         * This methods transforms the Parcel object back to a Checkup object.
         *
         * @param in The saved instance of class Checkup.
         * @return An instance of the class Checkup, which has been saved in in.
         */
        @Override
        public Checkup createFromParcel(Parcel in) {
            return new Checkup(in);
        }

        /**
         * This methods creates an array of the objects of type Checkup for a given size.
         *
         * @param size Size of array.
         * @return Array of objects of type Checkup for the given size.
         */
        @Override
        public Checkup[] newArray(int size) {
            return new Checkup[size];
        }
    };

    /**
     * Setter for the attribute date.
     *
     * @param date The date of checkup.
     */
    public void setDate(String[] date) {
        this.date = date;
    }

    /**
     * Setter for the attribute areas.
     *
     * @param areas Areas of patient's body, being checked.
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * Setter for the attribute doctor.
     *
     * @param doctor The doctor, who is responsible for the checkup.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Setter for the attribute findings.
     *
     * @param findings Findings during the checkup.
     */
    public void setFindings(String findings) {
        this.findings = findings;
    }

    /**
     * Getter of the attribute date.
     *
     * @return Date of Checkup.
     */
    public String[] getDate() {
        return date;
    }

    /**
     * Getter of the attribute doctor.
     *
     * @return Doctor responsible for the Checkup.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Getter of the attribute areas.
     *
     * @return Areas being checked.
     */
    public String getAreas() {
        return areas;
    }

    /**
     * Getter of the attribute findings.
     *
     * @return Findings during the checkup.
     */
    public String getFindings() {
        return findings;
    }

    @Override
    public String toString() {
        return String.format("Done by \"%s\" on %s.%s.%s", this.getDoctor(), this.getDate()[2], this.getDate()[1], this.getDate()[0]);
    }

    /**
     * The method to identify child classes, if the class has any.
     *
     * @return Number of child classes (normally just arbitrary values).
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *The method to write an object of type Checkup to Parcel.
     *
     * @param dest The Parcel object, which contains the object of type Checkup.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(date);
        dest.writeParcelable(doctor, flags);
        dest.writeString(areas);
        dest.writeString(findings);
    }
}
