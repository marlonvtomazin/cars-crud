package com.marlonvtomazin.carscrud.client.service;

import com.marlonvtomazin.carscrud.car.entity.Car;
import com.marlonvtomazin.carscrud.client.entity.Client;
import com.marlonvtomazin.carscrud.client.repository.ClientRepository;
import com.marlonvtomazin.carscrud.exception.CarUniqueViolationException;
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
       Client savedClient = clientRepository.save(client);
       return savedClient;
//        try {
//            Client savedClient = clientRepository.save(client);
//            log.info("Car registered successfully with ID: {}", savedClient.getId());
//            return savedClient;
//        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
//            log.error("Failed to register client: plate '{}' already exists", client.getDocument());
//            throw new CarUniqueViolationException(String.format("Car '%s' already registered", client.getName()));
//        }
    }
}
