package com.example.visitech;

public class Doctor {
    private String firstName;
    private String lastName;
    private int personalnumber;

    public Doctor(String firstName, String lastName, int personalnumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalnumber = personalnumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getPersonalnumber() {
        return personalnumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPersonalnumber(int personalnumber) {
        this.personalnumber = personalnumber;
    }

    @Override
    public String toString() {
        return String.format("%s, %s [%d]", this.getLastName(), this.getFirstName(), this.getPersonalnumber());
    }
}
