package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A patient.
 *
 * This class represents a patient with all attributes.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class Patient implements Parcelable{
    private static final String LOG = "Patient";
    private String firstName;
    private String lastName;
    private String[] birthday;
    private String[] admissionDate;
    private List<Checkup> checkups;
    private List<Medication> medications;
    private String sex;
    private int bedNr;
    private int age;

    /**
     * Custom constructor of the class.
     *
     * All parameters are details from patient. No comment needed.
     *
     * @param firstName
     * @param lastName
     * @param birthday
     * @param admissionDate
     * @param checkups
     * @param medications
     * @param sex
     * @param bedNr
     * @param age
     */
    public Patient(String firstName, String lastName, String[] birthday, String[] admissionDate, List<Checkup> checkups, List<Medication> medications, String sex, int bedNr, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.admissionDate = admissionDate;
        this.checkups = new ArrayList<>();
        this.checkups.addAll(checkups);
        this.medications = new ArrayList<>();
        this.medications.addAll(medications);
        this.sex = sex;
        this.bedNr = bedNr;
        this.age = age;
    }

    /**
     * The constructor for making an instance from a parcelable object.
     *
     * @param in The parcelable object, which contains the saved instance.
     */
    protected Patient(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.createStringArray();
        admissionDate = in.createStringArray();
        checkups = in.createTypedArrayList(Checkup.CREATOR);
        medications = in.createTypedArrayList(Medication.CREATOR);
        sex = in.readString();
        bedNr = in.readInt();
        age = in.readInt();
    }

    /**
     * The Creator of a parcelable object for the class Patient, which contains useful methods to
     * make an instance of class Patient and also an array of the instances of the class Patient.
     */
    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        /**
         * This methods transforms the Parcel object back to a Patient object.
         *
         * @param in The saved instance of class Patient.
         * @return An instance of the class Patient, which was saved in in.
         */
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        /**
         * This methods creates an array of the objects of type Patients for a given size.
         *
         * @param size Size of array.
         * @return Array of objects of type Patient for the given size.
         */
        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    /**
     * Getter for birthday.
     *
     * @return Birthday of patient.
     */
    public String[] getBirthday() {
        return birthday;
    }

    /**
     * Getter for age.
     *
     * @return Age of patient.
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for bedNr.
     *
     * @return Bed number of patient.
     */
    public int getBedNr() {
        return bedNr;
    }

    /**
     * Getter for checkups.
     *
     * @return List of checkups of patient.
     */
    public List<Checkup> getCheckups() {
        return checkups;
    }

    /**
     * Getter for medications.
     *
     * @return List of medications of patient.
     */
    public List<Medication> getMedications() {
        return medications;
    }

    /**
     * Getter for firstName.
     *
     * @return First Name of patient.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for lastName.
     *
     * @return Last Name of patient.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for sex.
     *
     * @return Sex of patient.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Getter for admissionDate.
     *
     * @return Admission Date of patient.
     */
    public String[] getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Setter for age.
     *
     * @param age Intended age of patient.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setter for bedNr.
     *
     * @param bedNr Intended bed number of patient.
     */
    public void setBedNr(int bedNr) {
        this.bedNr = bedNr;
    }

    /**
     * Setter for birthday.
     * @param birthday Intended birthday of patient.
     */
    public void setBirthday(String[] birthday) {
        this.birthday = birthday;
    }

    /**
     * Setter for checkups.
     * @param checkups Intended checkups of patient.
     */
    public void setCheckups(List<Checkup> checkups) {
        this.checkups = checkups;
    }

    /**
     * Setter for firstName.
     * @param firstName Intended first name of patient.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for lastName.
     *
     * @param lastName Intended last name of patient.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter for medications.
     *
     * @param medications Intended medications of patient.
     */
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    /**
     * Setter of sex.
     *
     * @param sex Intended sex of patient.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Setter of admissionDate.
     *
     * @param admissionDate Intended admission date of patient.
     */
    public void setAdmissionDate(String[] admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public String toString() {
        return String.format("Bednumber: %d   Name: %s", this.getBedNr(), this.getLastName());
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
     * The method to write an object of type Patient to Parcel.
     *
     * @param dest The Parcel object, which contains the object of type Patient.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeStringArray(birthday);
        dest.writeStringArray(admissionDate);
        dest.writeTypedList(checkups);
        dest.writeTypedList(medications);
        dest.writeString(sex);
        dest.writeInt(bedNr);
        dest.writeInt(age);
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            Log.d(LOG, "everything bad1");
            return false;
        }

        if(getClass() != o.getClass()){
            Log.d(LOG, "everything bad2");
            return false;
        }

        Patient other = (Patient) o;

        if(this.getLastName().equals(other.getLastName())){
            Log.d(LOG, "everything ok");
        }

        // Return if last names are equal (It could be much better).
        return this.getLastName().equals(other.getLastName());
    }
}
