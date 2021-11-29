package com.example.cardetails.controllers;

import com.example.cardetails.models.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class CarDetailsApi {
    private final ArrayList<Car> cars;

    public CarDetailsApi(ArrayList<Car> cars) {
        this.cars = cars;
    }

    @GetMapping(value = "/details/{id}")
    public Car details(@PathVariable(name = "id") int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    @GetMapping(value = "/reserved/{id}")
    public void reservedCar(@PathVariable(name = "id") int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setReserved(true);
                return;
            }
        }
    }

    @GetMapping(value = "/unreserved/{id}")
    public void unReservedCar(@PathVariable(name = "id") int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setReserved(false);
                return;
            }
        }
    }
}
