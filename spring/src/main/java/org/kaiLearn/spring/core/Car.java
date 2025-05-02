package org.kaiLearn.spring.core;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Car  implements Vehicle {

    // Create a move method
@Override
    public void move(){
        System.out.println("Car is moving ..");
    }
}
