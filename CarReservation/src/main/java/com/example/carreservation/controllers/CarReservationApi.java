package com.example.carreservation.controllers;

import com.example.carreservation.models.Car;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class CarReservationApi {
    private final ArrayList<Car> cars;

    public CarReservationApi(ArrayList<Car> cars) {
        this.cars = cars;
    }

    @GetMapping(value = "/not-reserved-cars")
    public ArrayList<Integer> getAvailableCars() {
        ArrayList<Integer> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isReserved()) {
                availableCars.add(car.getId());
            }
        }
        return availableCars;
    }

    @GetMapping(value = "/reserve/{id}")
    public String reserveCar(@PathVariable(name = "id") int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setReserved(true);
                return "Car was reserved successfully";
            }
        }
        return "Car wasn't found";

    }

    @GetMapping(value = "/unreserve/{id}")
    public String unReserveCar(@PathVariable(name = "id") int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setReserved(false);
                return "Car was unreserved";
            }
        }
        return "Car wasn't found";
    }
}
