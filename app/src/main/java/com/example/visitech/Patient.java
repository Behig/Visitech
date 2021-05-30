package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Patient implements Parcelable{
    private String firstName;
    private String lastName;
    private String[] birthday;
    private String[] admissionDate;
    private List<Checkup> checkups;
    private List<Medication> medications;
    private String sex;
    private int bedNr;
    private int age;


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

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public String[] getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public int getBedNr() {
        return bedNr;
    }

    public List<Checkup> getCheckups() {
        return checkups;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String[] getAdmissionDate() {
        return admissionDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBedNr(int bedNr) {
        this.bedNr = bedNr;
    }

    public void setBirthday(String[] birthday) {
        this.birthday = birthday;
    }

    public void setCheckups(List<Checkup> checkups) {
        this.checkups = checkups;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAdmissionDate(String[] admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public String toString() {
        return String.format("Bednumber: %d   Name: %s", this.getBedNr(), this.getLastName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
}
