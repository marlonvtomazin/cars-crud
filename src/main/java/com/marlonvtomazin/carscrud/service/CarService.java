package com.marlonvtomazin.carscrud.service;

import com.marlonvtomazin.carscrud.entity.Car;
import com.marlonvtomazin.carscrud.exception.CarUniqueViolationException;
import com.marlonvtomazin.carscrud.exception.EntityNotFoundException;
import com.marlonvtomazin.carscrud.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;

    @Transactional
    public Car save(Car car) {
        try {
            return carRepository.save(car);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new CarUniqueViolationException(String.format("Car '%s' already registered", car.getPlate()));
        }
    }

    @Transactional(readOnly = true)
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(String.format("Car '%s' not found", id))
        );
    }

    @Transactional
    public Car updatePartial(Long id, Car carDetails) {
        Car foundCar = this.findById(id);

        if (carDetails.getPlate() != null) {
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

        return carRepository.save(foundCar);
    }

    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        Car foundCar = this.findById(id);
        carRepository.delete(foundCar);
    }
}
