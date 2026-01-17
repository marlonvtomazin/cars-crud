package com.marlonvtomazin.carscrud.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CarUpdateDto {
    @Size(min = 8, max = 8)
    // We have "Mercosul" plate but I used this pattern for study and test
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "The car plate must follow this pattern 'XXX-0000'")
    private String plate;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private Integer kilometers;
}
