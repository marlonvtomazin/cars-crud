package com.marlonvtomazin.carscrud.web.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CarUpdateDto {
    @Size(min = 8, max = 8, message="Size must be between 8 and 8")
    // We have "Mercosul" plate but I used this pattern for study and test
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "The car plate must follow this pattern 'XXX-0000'")
    private String plate;
    @Size(min = 1, message = "Brand can't be blank")
    private String brand;
    @Size(min = 1, message = "Model can't be blank")
    private String model;
    @Size(min = 1, message = "Color can't be blank")
    private String color;
    @Min(value=1, message = "Year can't be blank")
    private Integer carYear;
    @Min(value=1, message = "Odometer can't be blank")
    private Integer odometer;
}
