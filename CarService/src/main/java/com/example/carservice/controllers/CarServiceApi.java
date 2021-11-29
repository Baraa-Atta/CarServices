package com.example.carservice.controllers;

import com.example.carservice.config.DetailsConfig;
import com.example.carservice.config.ReservationConfig;
import com.example.carservice.models.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@RestController
public class CarServiceApi {
    private Logger logger = LoggerFactory.getLogger(CarServiceApi.class);
    private final ReservationConfig reservationConfig;
    private final DetailsConfig detailsConfig;
    private final String carReservationHost;
    private final String carDetailsHost;

    public CarServiceApi(ReservationConfig reservationConfig, DetailsConfig detailsConfig) {
        this.reservationConfig = reservationConfig;
        this.detailsConfig = detailsConfig;
        this.carReservationHost = "http://" + this.reservationConfig.getHost() + ":" + this.reservationConfig.getPort();
        this.carDetailsHost = "http://" + this.detailsConfig.getHost() + ":" + this.detailsConfig.getPort();
    }

    @GetMapping(value = "/available-cars")
    public ArrayList<Car> getCars() {
        RestTemplate restTemplate = new RestTemplate();
        String url = carReservationHost + "/not-reserved-cars";
        ArrayList<Integer> availableCarsIds = restTemplate.getForObject(url, ArrayList.class);
        ArrayList<Car> availableCars = new ArrayList<>();

        if (availableCarsIds != null) {
            for (Integer id : availableCarsIds) {
                String detailsUrl = carDetailsHost + "/details/" + id;
                Car car = restTemplate.getForObject(detailsUrl, Car.class);
                availableCars.add(car);

            }
        }
        return availableCars;

    }

    @GetMapping(value = "/car-details/{id}")
    public Car getCarDetails(@PathVariable(name = "id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = carDetailsHost + "/details/" + id;
        logger.info("URL" + url);
        return restTemplate.getForObject(url, Car.class);
    }

    @GetMapping(value = "/reserve/{id}")
    public String reserveCar(@PathVariable(name = "id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = carReservationHost + "/reserve/" + id;
        String detailsUrl = carDetailsHost + "/reserved/" + id;
        restTemplate.getForObject(detailsUrl, Void.class);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/unreserve/{id}")
    public String unReserveCar(@PathVariable(name = "id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = carReservationHost + "/unreserve/" + id;
        String detailsUrl = carDetailsHost + "/unreserved/" + id;
        restTemplate.getForObject(detailsUrl, Void.class);
        return restTemplate.getForObject(url, String.class);
    }
}
