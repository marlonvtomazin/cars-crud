package com.marlonvtomazin.carscrud.client.repository;

import com.marlonvtomazin.carscrud.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
