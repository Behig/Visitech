package com.example.visitech;

public class Medication {
    private String name;
    private double dose;
    private Day weekDay;

    public Medication(String name, double dose, Day weekDay){
        this.name = name;
        this.dose = dose;
        this.weekDay = weekDay;
    }

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
}
