package com.beesion.ms.test.service;

import com.beesion.ms.model.Address;
import java.util.List;

/**
 * Interface que define los servicios disponibles para la entidad Address
 * Implementa CRUD básico como se solicita
 */
public interface AddressService {
    
    /**
     * Obtiene todas las direcciones
     * @return Lista de direcciones
     */
    List<Address> getAllAddresses();
    
    /**
     * Obtiene una dirección por su ID
     * @param id Identificador de la dirección
     * @return Address o null si no existe
     */
    Address getAddressById(Long id);
    
    /**
     * Crea una nueva dirección
     * @param address Datos de la dirección a crear
     * @return Address creada con su ID asignado
     */
    Address createAddress(Address address);
    
    /**
     * Actualiza una dirección existente
     * @param id Identificador de la dirección
     * @param address Nuevos datos de la dirección
     * @return Address actualizada o null si no existe
     */
    Address updateAddress(Long id, Address address);
    
    /**
     * Elimina una dirección por su ID
     * @param id Identificador de la dirección
     * @return true si se eliminó, false si no existía
     */
    boolean deleteAddress(Long id);
    
    /**
     * Busca direcciones por ciudad
     * @param city Nombre de la ciudad
     * @return Lista de direcciones
     */
    List<Address> getAddressesByCity(String city);
    
    /**
     * Busca direcciones por país
     * @param country Nombre del país
     * @return Lista de direcciones
     */
    List<Address> getAddressesByCountry(String country);

    /**
     * Obtiene las direcciones de una persona específica
     * @param personId ID de la persona
     * @return Lista de direcciones de la persona
     */
    List<Address> getAddressesByPersonId(Long personId);
}