package com.marlonvtomazin.carscrud.client.web.controller;

import com.marlonvtomazin.carscrud.car.web.dto.mapper.CarMapper;
import com.marlonvtomazin.carscrud.client.entity.Client;
import com.marlonvtomazin.carscrud.client.service.ClientService;
import com.marlonvtomazin.carscrud.client.web.dto.ClientResponseDto;
import com.marlonvtomazin.carscrud.client.web.dto.ClientUpdateDto;
import com.marlonvtomazin.carscrud.client.web.dto.mapper.ClientMapper;
import com.marlonvtomazin.carscrud.client.web.dto.ClientCreateDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getById (@Valid @PathVariable Long id) {
        log.info("REST request to get Client by ID : {}", id);
        Client foundClient = clientService.findById(id);
        return ResponseEntity.ok(ClientMapper.toDto(foundClient));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updatePartial(@PathVariable Long id, @Valid @RequestBody ClientUpdateDto updateDto) {
        log.info("REST request to partially update Client ID : {}", id);
        Client updatedClient = clientService.updatePartial(id, ClientMapper.toUpdateEntity(updateDto));
        return ResponseEntity.ok(ClientMapper.toDto(updatedClient));
    }

    @GetMapping()
    public ResponseEntity<List<ClientResponseDto>> getAll () {
        log.info("REST request to get all Clients");
        List<Client> foundClients = clientService.findAll();
        return ResponseEntity.ok(ClientMapper.toListDto(foundClients));
    }
}
