package com.marlonvtomazin.carscrud.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CarCreateDto {
    @NotBlank(message = "Plate can't be blank")
    @Size(min = 8, max = 8, message="Size must be between 8 and 8")
    // We have "Mercosul" plate but I used this pattern for study and test
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "The car plate must follow this pattern 'XXX-0000'")
    private String plate;
    @NotBlank(message = "Brand can't be blank")
    private String brand;
    @NotBlank(message = "Model can't be blank")
    private String model;
    @NotBlank(message = "Color can't be blank")
    private String color;
    @NotNull(message = "Year can't be null")
    private Integer carYear;
    @NotNull(message = "Kilometers can't be null")
    private Integer kilometers;
}
