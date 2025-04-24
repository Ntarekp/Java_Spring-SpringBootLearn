package org.kaiLearn.spring.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Traveler {

    private Vehicle vehicle;

    @Autowired
    public Traveler(Vehicle vehicle) {
    this.vehicle =vehicle;
    }

    public void startJourney(){
       this.vehicle.move();
    }
}
