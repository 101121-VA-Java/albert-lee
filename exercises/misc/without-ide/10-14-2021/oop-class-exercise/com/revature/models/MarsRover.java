package com.revature.models;

public class MarsRover {
    public static boolean canFlyThroughSpace = true;
    public static boolean entertainsHumans = true;
    public String name;
    public double budget;
    // assuming space is 1 dimensional
    public double position = 0;

    public MarsRover(String name, double budget) {
        this.name = name;
        this.budget = budget * 5.00;
    }

    public void flyForward(int velocity){
        this.position++;
    }

    public void flyBackward(int velocity){
        this.position--;
    }
}