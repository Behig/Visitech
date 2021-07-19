package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * A medication.
 *
 * This is a class representing a medication, which contains details like name, dose and
 * the weekday, on that the medication should be taken by the patient.
 */
public class Medication implements Parcelable {
    private String name;
    private double dose;
    private Day weekDay;

    /**
     * Custom constructor of class.
     *
     * @param name Name of medication.
     * @param dose Dose of medication.
     * @param weekDay The weekday, on that the medication should be taken by the patient.
     */
    public Medication(String name, double dose, Day weekDay){
        this.name = name;
        this.dose = dose;
        this.weekDay = weekDay;
    }

    /**
     * The constructor for making an instance from a parcelable object.
     *
     * @param in The parcelable object, which contains the saved instance.
     */
    protected Medication(Parcel in) {
        name = in.readString();
        dose = in.readDouble();
        weekDay = Day.valueOf(in.readString());
    }

    /**
     * The Creator of a parcelable object for the class Medication, which contains useful methods to
     * make an instance of class Medication and also an array of the instances of the class Medication.
     */
    public static final Creator<Medication> CREATOR = new Creator<Medication>() {
        /**
         * This methods transforms the Parcel object back to a Medication object.
         *
         * @param in The saved instance of class Medication.
         * @return An instance of the class Medication, which was saved in in.
         */
        @Override
        public Medication createFromParcel(Parcel in) {
            return new Medication(in);
        }

        /**
         * This methods creates an array of the objects of type Medication for a given size.
         *
         * @param size Size of array.
         * @return Array of objects of type Medication for the given size.
         */
        @Override
        public Medication[] newArray(int size) {
            return new Medication[size];
        }
    };

    /**
     * Getter for weekday.
     *
     * @return Weekday of medication.
     */
    public Day getWeekDay() {
        return weekDay;
    }

    /**
     * Getter for dose.
     *
     * @return Dose of medication.
     */
    public double getDose() {
        return dose;
    }

    /**
     * Getter for name of medication.
     *
     * @return Name of medication.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the dose.
     *
     * @param dose Intended dose of medication.
     */
    public void setDose(double dose) {
        this.dose = dose;
    }

    /**
     * Setter for the name of medication.
     *
     * @param name Intended name of medication.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the weekday of medication.
     *
     * @param weekDay Intended weekday of medication.
     */
    public void setWeekDay(Day weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString(){
        return String.format("%.1f dose of %s", this.getDose(), this.getName());
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
     * The method to write an object of type Medication to Parcel.
     *
     * @param dest The Parcel object, which contains the object of type Medication.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(dose);
        dest.writeString(this.getWeekDay().name());
    }
}
