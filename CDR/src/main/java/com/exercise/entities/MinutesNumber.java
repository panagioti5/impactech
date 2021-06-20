package com.exercise.entities;

public class MinutesNumber {

    private double successfulMinutes;
    private double incompleteMinutes;

    public MinutesNumber(double successfulMinutes, double incompleteMinutes) {
        this.successfulMinutes = successfulMinutes;
        this.incompleteMinutes = incompleteMinutes;
    }

    public MinutesNumber(){}

    public double getSuccessfulMinutes() {
        return successfulMinutes;
    }

    public void setSuccessfulMinutes(double successfulMinutes) {
        this.successfulMinutes = incompleteMinutes;
    }

    public double getIncompleteMinutes() {
        return incompleteMinutes;
    }

    public void setIncompleteMinutes(double incompleteMinutes) {
        this.incompleteMinutes = incompleteMinutes;
    }
}
