package com.marlonvtomazin.carscrud.client.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ClientUpdateDto {
    @Size(min = 11, max = 20, message="Size must be between 11 and 20")
    private String document; //cpf or cnpj
    private String name;
    private String phone;
    @Email(message="Invalid email")
    private String email;
}
