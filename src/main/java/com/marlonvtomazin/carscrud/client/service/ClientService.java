package com.marlonvtomazin.carscrud.client.service;

import com.marlonvtomazin.carscrud.car.entity.Car;
import com.marlonvtomazin.carscrud.client.entity.Client;
import com.marlonvtomazin.carscrud.client.repository.ClientRepository;
import com.marlonvtomazin.carscrud.exception.EntityNotFoundException;
import com.marlonvtomazin.carscrud.exception.UniqueConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        try {
            Client savedClient = clientRepository.save(client);
            log.info("Client registered successfully with ID: {}", savedClient.getId());
            return savedClient;
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            log.error("Failed to register client: document '{}' already exists", client.getDocument());
            throw new UniqueConstraintViolationException(String.format("Client '%s' already registered", client.getName()));
        }
    }

    @Transactional(readOnly = true)
    public Client findById(@Valid Long id) {
        log.debug("Searching for client with ID: {}", id);
        return clientRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Search failed: client with ID {} not found", id);
                    return new EntityNotFoundException(String.format("Client '%s' not found", id));
                }
        );
    }

    @Transactional
    public Client updatePartial(Long id, Client clientDetails) {
        log.info("Starting partial update for car ID: {}", id);
        Client foundClient = this.findById(id);

        if (clientDetails.getDocument() != null && clientRepository.existsByDocumentAndIdNot(clientDetails.getDocument(), id)) {
            log.error("Update failed: document '{}' is already taken by another client", clientDetails.getDocument());
            throw new UniqueConstraintViolationException(String.format("Client '%s' already registered", clientDetails.getDocument()));
        }

        if (clientDetails.getDocument() != null) {
            log.debug("Updating document for ID {}: {} -> {}", id, foundClient.getDocument(), clientDetails.getDocument());
            foundClient.setDocument(clientDetails.getDocument());
        }
        if (clientDetails.getName() != null) {
            foundClient.setName(clientDetails.getName());
        }
        if (clientDetails.getEmail() != null) {
            foundClient.setEmail(clientDetails.getEmail());
        }
        if (clientDetails.getPhone() != null) {
            foundClient.setPhone(clientDetails.getPhone());
        }

        Client updated = clientRepository.save(foundClient);
        log.info("Client ID {} updated successfully", id);
        return updated;
    }
}
