package com.example.visitech;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Doctor implements Parcelable {
    private String firstName;
    private String lastName;
    private int personalnumber;

    public Doctor(String firstName, String lastName, int personalnumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalnumber = personalnumber;
    }

    protected Doctor(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        personalnumber = in.readInt();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(personalnumber);
    }
}
