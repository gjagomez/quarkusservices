package com.beesion.ms.test.repository;

import com.beesion.ms.model.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repositorio básico para Address usando Panache
 */
@ApplicationScoped
public class AddressRepo implements PanacheRepository<Address> {
    
}