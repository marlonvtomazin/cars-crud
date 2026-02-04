package com.marlonvtomazin.carscrud.car.repository;

import com.marlonvtomazin.carscrud.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByPlateAndIdNot(String plate, Long id);
}