package com.marlonvtomazin.carscrud.car.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CarResponseDto {
    private String id;
    private String plate;
    private String brand;
    private String model;
    private String color;
    private Integer carYear;
    private Integer odometer;
    private String clientId;
}
