// Bicycle.java
package com.rgt.vehiclerental;

public class Bicycle extends Vehicle {
    private int numberOfGears;

    public Bicycle(String licensePlate, String make, String model, int numberOfGears) {
        super(licensePlate, make, model);
        this.numberOfGears = numberOfGears;
    }

    // Bicycle-specific properties and methods
}
