package com.marlonvtomazin.carscrud.service;

import com.marlonvtomazin.carscrud.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
}
