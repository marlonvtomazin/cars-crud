package com.marlonvtomazin.carscrud.web.controller;

import com.marlonvtomazin.carscrud.entity.Car;
import com.marlonvtomazin.carscrud.service.CarService;
import com.marlonvtomazin.carscrud.web.dto.CarCreateDto;
import com.marlonvtomazin.carscrud.web.dto.CarResponseDto;
import com.marlonvtomazin.carscrud.web.dto.CarUpdateDto;
import com.marlonvtomazin.carscrud.web.dto.mapper.CarMapper;
import com.marlonvtomazin.carscrud.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cars", description = "All operations for creating, editing, listing, and deleting items related to cars.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private final CarService carService;

    @Operation(summary = "Register a new car", description = "Resource to create a new car.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Car registered with success",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponseDto.class))),
                @ApiResponse(responseCode = "409", description = "Car already registered",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422", description = "Resource unprocessed, invalid input data",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<CarResponseDto> create (@Valid @RequestBody CarCreateDto createDto) {
        log.info("REST request to save Car : {}", createDto.getPlate());
        Car savedCar = carService.save(CarMapper.toCreateEntity(createDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CarMapper.toDto(savedCar));
    }

    @Operation(summary = "Find car by id", description = "Find car using id.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Car found.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponseDto.class))),
                @ApiResponse(responseCode = "404", description = "Resource not found",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> getById (@Valid @PathVariable Long id) {
        log.info("REST request to get Car by ID : {}", id);
        Car foundCar = carService.findById(id);
        return ResponseEntity.ok(CarMapper.toDto(foundCar));
    }

    @Operation(summary = "Update a car partially/full", description = "Update one or more field of an existing car byt its id",
            responses = {
                @ApiResponse(responseCode = "200", description = "Car updated successfully",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponseDto.class))),
                @ApiResponse(responseCode = "404", description = "Resource not found",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422", description = "Resource unprocessed, invalid input data",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<CarResponseDto> updatePartial(@PathVariable Long id, @Valid @RequestBody CarUpdateDto updateDto) {
        log.info("REST request to partially update Car ID : {}", id);
        Car updatedCar = carService.updatePartial(id, CarMapper.toUpdateEntity(updateDto));
        return ResponseEntity.ok(CarMapper.toDto(updatedCar));
    }

    @Operation(summary = "List all cars", description = "List all cars registered",
            responses = {
                @ApiResponse(responseCode = "200", description = "List with all cars registered",
                        content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CarResponseDto.class))))
            }
    )
    @GetMapping()
    public ResponseEntity<List<CarResponseDto>> getAll () {
        log.info("REST request to get all Cars");
        List<Car> foundCars = carService.findAll();
        return ResponseEntity.ok(CarMapper.toListDto(foundCars));
    }
    @Operation(summary = "Delete a car", description = "Delete a car by its id",
            responses = {
                @ApiResponse(responseCode = "204", description = "Car deleted successfully",
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "404", description = "Car not found",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete (@PathVariable Long id) {
        log.info("REST request to delete Car ID : {}", id);
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
