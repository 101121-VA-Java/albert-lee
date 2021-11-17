package com.revature.driver;
import com.revature.models.MarsRover;

public class Driver {
    public static void main(String[] args) {
        MarsRover curiosity = new MarsRover("Curiosity", 25300000);
        System.out.println(curiosity.name);
    }
}