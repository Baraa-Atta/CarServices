package com.example.carreservation;

import com.example.carreservation.models.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class CarReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarReservationApplication.class, args);
    }

    @Bean
    public ArrayList<Car> cars(){
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("BMW", 0, false));
        cars.add(new Car("Toyota", 1, false));
        cars.add(new Car("Hyundai", 2, false));
        cars.add(new Car("Range Rover", 3, false));
        return cars;
    }
}
