package com.beesion.ms.test.repository;


import com.beesion.ms.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repositorio básico para Person usando Panache
 */
@ApplicationScoped
public class PersonRepo implements PanacheRepository<Person> {
    
  
}