package com.marlonvtomazin.carscrud.web.controller;

import com.marlonvtomazin.carscrud.entity.Car;
import com.marlonvtomazin.carscrud.service.CarService;
import com.marlonvtomazin.carscrud.web.dto.CarCreateDto;
import com.marlonvtomazin.carscrud.web.dto.CarResponseDto;
import com.marlonvtomazin.carscrud.web.dto.CarUpdateDto;
import com.marlonvtomazin.carscrud.web.dto.mapper.CarMapper;
import jakarta.validation.Valid;
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
    public ResponseEntity<CarResponseDto> create (@Valid @RequestBody CarCreateDto createDto) {
        Car savedCar = carService.save(CarMapper.toCreateEntity(createDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CarMapper.toDto(savedCar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> getById (@Valid @PathVariable Long id) {
        Car foundCar = carService.findById(id);
        return ResponseEntity.ok(CarMapper.toDto(foundCar));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarResponseDto> updatePartial(@PathVariable Long id, @Valid @RequestBody CarUpdateDto updateDto) {
        Car updatedCar = carService.updatePartial(id, CarMapper.toUpdateEntity(updateDto));
        return ResponseEntity.ok(CarMapper.toDto(updatedCar));
    }

    @GetMapping()
    public ResponseEntity<List<CarResponseDto>> getAll () {
        List<Car> foundCars = carService.findAll();
        return ResponseEntity.ok(CarMapper.toListDto(foundCars));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete (@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
