package com.marlonvtomazin.carscrud.web.controller;

import com.marlonvtomazin.carscrud.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private final CarService carService;
}
