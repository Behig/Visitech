package com.example.visitech;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private String firstName;
    private String lastName;
    private Date birthday;
    private Date admissionDate;
    private List<Checkup> checkups;
    private List<Medication> medications;
    private String sex;
    private int bedNr;
    private int age;


    public Patient(String firstName, String lastName, Date birthday, Date admissionDate, List<Checkup> checkups, List<Medication> medications, String sex, int bedNr, int age){
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

    public Date getBirthday() {
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

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBedNr(int bedNr) {
        this.bedNr = bedNr;
    }

    public void setBirthday(Date birthday) {
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

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public String toString() {
        return String.format("BedNr.%d Name:%s", this.getBedNr(), this.getLastName());
    }

}
