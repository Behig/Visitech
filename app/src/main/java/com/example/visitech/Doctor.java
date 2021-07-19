package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * A doctor.
 *
 * This is a class representing a doctor, which contains details like first name, last name and
 * personal number of doctor.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class Doctor implements Parcelable {
    private String firstName;
    private String lastName;
    private int personalnumber;

    /**
     * Custom constructor of class.
     *
     * @param firstName First name of doctor.
     * @param lastName Last name of doctor.
     * @param personalnumber personal number of doctor.
     */
    public Doctor(String firstName, String lastName, int personalnumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalnumber = personalnumber;
    }

    /**
     *The constructor for making an instance from a parcelable object.
     *
     * @param in The parcelable object, which contains the saved instance.
     */
    protected Doctor(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        personalnumber = in.readInt();
    }

    /**
     * The Creator of a parcelable object for the class Doctor, which contains useful methods to
     * make an instance of class Doctor and also an array of the instances of the class Doctor.
     */
    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        /**
         * This methods transforms the Parcel object back to a Doctor object.
         *
         * @param in The saved instance of class Doctor.
         * @return An instance of the class Doctor, which was saved in in.
         */
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        /**
         * This methods creates an array of the objects of type Doctor for a given size.
         *
         * @param size Size of array.
         * @return Array of objects of type Doctor for the given size.
         */
        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };


    /**
     * Getter for attribute lastName.
     *
     * @return Last name of doctor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for attribute firstName.
     *
     * @return First name of doctor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for attribute personal number.
     *
     * @return Personal number of doctor.
     */
    public int getPersonalnumber() {
        return personalnumber;
    }

    /**
     * Setter for attribute lastName.
     *
     * @param lastName Last name of doctor.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter for attribute firstName.
     *
     * @param firstName First name of doctor.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for attribute personalnumber.
     *
     * @param personalnumber Personal number of doctor.
     */
    public void setPersonalnumber(int personalnumber) {
        this.personalnumber = personalnumber;
    }

    @Override
    public String toString() {
        return String.format("%s, %s [%d]", this.getLastName(), this.getFirstName(), this.getPersonalnumber());
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
     * The method to write an object of type Doctor to Parcel.
     *
     * @param dest The Parcel object, which contains the object of type Doctor.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(personalnumber);
    }
}
