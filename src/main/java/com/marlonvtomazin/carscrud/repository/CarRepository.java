package com.marlonvtomazin.carscrud.repository;

import com.marlonvtomazin.carscrud.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}