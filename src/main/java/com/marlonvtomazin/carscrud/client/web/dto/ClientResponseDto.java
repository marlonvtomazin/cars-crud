package com.marlonvtomazin.carscrud.client.web.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ClientResponseDto {
    private String id;
    private String name;
    private String document;
    private String phone;
    private String email;
}
