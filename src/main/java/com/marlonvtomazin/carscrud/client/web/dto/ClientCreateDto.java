package com.marlonvtomazin.carscrud.client.web.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ClientCreateDto {
    @NotBlank(message = "Document can't be blank")
    @Size(min = 11, max = 20, message="Size must be between 11 and 20")
    private String document; //cpf or cnpj

    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotBlank(message = "Phone can't be blank")
    private String phone;

    @NotBlank(message = "Email can't be blank")
    @Email(message="Invalid email")
    private String email;
}
