package com.marlonvtomazin.carscrud.client.web.controller;

import com.marlonvtomazin.carscrud.client.entity.Client;
import com.marlonvtomazin.carscrud.client.service.ClientService;
import com.marlonvtomazin.carscrud.client.web.dto.ClientResponseDto;
import com.marlonvtomazin.carscrud.client.web.dto.mapper.ClientMapper;
import com.marlonvtomazin.carscrud.client.web.dto.ClientCreateDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cars", description = "All operations for creating, editing, listing, and deleting items related to cars.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> create (@Valid @RequestBody ClientCreateDto createDto) {
        log.info("REST request to save Client : {}", createDto.getName());
        Client savedClient = clientService.save(ClientMapper.toCreateEntity(createDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ClientMapper.toDto(savedClient));
    }
}
