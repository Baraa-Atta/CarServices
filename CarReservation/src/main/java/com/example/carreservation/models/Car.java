package com.example.carreservation.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    String model;
    int id;
    boolean reserved;
}
