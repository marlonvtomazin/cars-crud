package com.marlonvtomazin.carscrud.web.controller;

import com.marlonvtomazin.carscrud.entity.Car;
import com.marlonvtomazin.carscrud.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> create (@RequestBody Car car) {
        Car savedCar = carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById (@PathVariable Long id) {
        Car foundCar = carService.findById(id);
        return ResponseEntity.ok(foundCar);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> updatePartial(@PathVariable Long id, @RequestBody Car carDetails) {
        Car updatedCar = carService.updatePartial(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAll () {
        List<Car> foundCars = carService.findAll();
        return ResponseEntity.ok(foundCars);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete (@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
