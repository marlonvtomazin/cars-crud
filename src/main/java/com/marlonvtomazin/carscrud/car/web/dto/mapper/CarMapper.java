package com.marlonvtomazin.carscrud.web.dto.mapper;

import com.marlonvtomazin.carscrud.car.entity.Car;
import com.marlonvtomazin.carscrud.web.dto.CarCreateDto;
import com.marlonvtomazin.carscrud.web.dto.CarResponseDto;
import com.marlonvtomazin.carscrud.web.dto.CarUpdateDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    // Could be same method for both but using two is easier to control the flow
    public static Car toCreateEntity(CarCreateDto carCreateDto){
        return modelMapper.map(carCreateDto, Car.class);
    }

    public static Car toUpdateEntity(CarUpdateDto carCreateDto){
        return modelMapper.map(carCreateDto, Car.class);
    }

    public static CarResponseDto toDto(Car car){
        return modelMapper.map(car, CarResponseDto.class);
    }

    public static List<CarResponseDto> toListDto(List<Car> cars) {
        //return cars.stream().map(car -> toDto(car)).collect(Collectors.toList());
        return cars.stream().map(CarMapper::toDto).collect(Collectors.toList());
    }
}
