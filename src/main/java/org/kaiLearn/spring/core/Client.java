package org.kaiLearn.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {

/*
        Vehicle vehicle = new Car();
        Traveler traveler = new Traveler(vehicle);

        traveler.startJourney();

*/
 //Creating a spring IoC container
 //Read the configuration class
 //Create and manage the spring beans.

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        /*
Retrieve spring beans from spring IoC container
        Car car = applicationContext.getBean(Car.class);
        car.move();

       Bike bike = applicationContext.getBean(Bike.class);
        bike.move();
*/

        Traveler traveler1 = applicationContext.getBean(Traveler.class);
        traveler1.startJourney();

    }
}

