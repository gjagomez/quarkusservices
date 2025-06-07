package com.beesion.ms.test.service;


import com.beesion.ms.model.Person;
import com.beesion.ms.model.Address;
import java.util.List;

/**
 * Interface que define los servicios disponibles para la entidad Person
 * Aplicando el principio de Responsabilidad Única (SOLID)
 */
public interface PersonService {
    
    /**
     * Obtiene todas las personas
     * @return Lista de personas
     */
    List<Person> getAllPersons();
    
    /**
     * Obtiene una persona por su ID
     * @param id Identificador de la persona
     * @return Person o null si no existe
     */
    Person getPersonById(Long id);
    
    /**
     * Crea una nueva persona
     * @param person Datos de la persona a crear
     * @return Person creada con su ID asignado
     */
    Person createPerson(Person person);
    
    /**
     * Actualiza una persona existente
     * @param id Identificador de la persona
     * @param person Nuevos datos de la persona
     * @return Person actualizada o null si no existe
     */
    Person updatePerson(Long id, Person person);
    
    /**
     * Elimina una persona por su ID
     * @param id Identificador de la persona
     * @return true si se eliminó, false si no existía
     */
    boolean deletePerson(Long id);
    
    /**
     * Busca personas por nombre (búsqueda parcial)
     * @param name Nombre o parte del nombre
     * @return Lista de personas que coinciden
     */
    List<Person> getPersonsByName(String name);
    
    /**
     * Obtiene todas las direcciones de una persona
     * @param personId ID de la persona
     * @return Lista de direcciones de la persona
     */
    List<Address> getPersonAddresses(Long personId);
}