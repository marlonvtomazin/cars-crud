package com.marlonvtomazin.carscrud.service;

import com.marlonvtomazin.carscrud.entity.Car;
import com.marlonvtomazin.carscrud.exception.CarUniqueViolationException;
import com.marlonvtomazin.carscrud.exception.EntityNotFoundException;
import com.marlonvtomazin.carscrud.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;

    @Transactional
    public Car save(Car car) {
        log.info("Attempting to register new car with plate: {}", car.getPlate());
        try {
            Car savedCar = carRepository.save(car);
            log.info("Car registered successfully with ID: {}", savedCar.getId());
            return savedCar;
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            log.error("Failed to register car: plate '{}' already exists", car.getPlate());
            throw new CarUniqueViolationException(String.format("Car '%s' already registered", car.getPlate()));
        }
    }

    @Transactional(readOnly = true)
    public Car findById(Long id) {
        log.debug("Searching for car with ID: {}", id);
        return carRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Search failed: car with ID {} not found", id);
                    return new EntityNotFoundException(String.format("Car '%s' not found", id));
                }
        );
    }

    @Transactional
    public Car updatePartial(Long id, Car carDetails) {
        log.info("Starting partial update for car ID: {}", id);
        Car foundCar = this.findById(id);

        if (carDetails.getPlate() != null && carRepository.existsByPlateAndIdNot(carDetails.getPlate(), id)) {
            log.error("Update failed: plate '{}' is already taken by another car", carDetails.getPlate());
            throw new CarUniqueViolationException(String.format("Car '%s' already registered", carDetails.getPlate()));
        }

        if (carDetails.getPlate() != null) {
            log.debug("Updating plate for ID {}: {} -> {}", id, foundCar.getPlate(), carDetails.getPlate());
            foundCar.setPlate(carDetails.getPlate());
        }
        if (carDetails.getBrand() != null) {
            foundCar.setBrand(carDetails.getBrand());
        }
        if (carDetails.getModel() != null) {
            foundCar.setModel(carDetails.getModel());
        }
        if (carDetails.getColor() != null) {
            foundCar.setColor(carDetails.getColor());
        }
        if (carDetails.getCarYear() != null) {
            foundCar.setCarYear(carDetails.getCarYear());
        }
        if (carDetails.getOdometer() != null) {
            foundCar.setOdometer(carDetails.getOdometer());
        }

        Car updated = carRepository.save(foundCar);
        log.info("Car ID {} updated successfully", id);
        return updated;
    }

    @Transactional(readOnly = true)
    public List<Car> findAll() {
        log.debug("Fetching all cars from database");
        return carRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        log.info("Request to delete car with ID: {}", id);
        Car foundCar = this.findById(id);
        carRepository.delete(foundCar);
        log.info("Car ID {} deleted successfully", id);
    }
}