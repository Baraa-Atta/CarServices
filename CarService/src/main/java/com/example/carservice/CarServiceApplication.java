package com.example.carservice;

import com.example.carservice.config.DetailsConfig;
import com.example.carservice.config.ReservationConfig;
import com.example.carservice.models.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(value = {ReservationConfig.class, DetailsConfig.class})
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

}
