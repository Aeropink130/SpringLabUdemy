package com.aeropink.springlab.service;


import com.aeropink.springlab.controller.ClientRequest;
import com.aeropink.springlab.entity.Client;
import com.aeropink.springlab.entity.ClientApartment;
import com.aeropink.springlab.repository.ClientApartmentRepository;
import com.aeropink.springlab.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.aeropink.springlab.repository.ClientApartmentSpecification.*;

@Service
public class ApartmentService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientApartmentRepository clientApartmentRepository;

//    public List<Client> getAllClients() {
//        return clientRepository.findAll();
//    }
//
//    public List<ClientApartment> getAllApartments() {
//        return clientApartmentRepository.findAll();
//    }

    public List<Client> findClients(String optionalClientId) {
        if (optionalClientId != null) {
            var clientsIds = List.of(
                    UUID.fromString(optionalClientId)
            );
            return clientRepository.findAllById(clientsIds);
        } else {
            return clientRepository.findAll();
        }
    }

    public List<ClientApartment> findApartments(
            String city, Integer minPrice, Integer maxPrice, Boolean isAvailableForRent) {

        Specification<ClientApartment> specification = Specification.where(null);

        if (city != null){
            specification = specification.and(cityContainsIgnoreCase(city));
        }

        if (minPrice != null && maxPrice != null){
            specification = specification.and(rentPriceBetween(minPrice, maxPrice));
        }

        if (isAvailableForRent != null) {
            specification = specification.and(isAvailableForRent(isAvailableForRent));
        }

        return clientApartmentRepository.findAll(specification);
    }

    public UUID createClient(ClientRequest clientRequest){
        var clientEntity = new Client();

        clientEntity.setEmail(clientRequest.getEmail());
        clientEntity.setPhone(clientRequest.getPhone());
        clientEntity.setFullName(clientRequest.getFullName());

        if (clientRequest.getApartments() != null){
            var clientApartmentEntities = new ArrayList<ClientApartment>();

            for (var apartmentRequest : clientRequest.getApartments()){
                var apartmentEntity = new ClientApartment();

                apartmentEntity.setAvailableForRent(apartmentRequest.getAvailableForRent());
                apartmentEntity.setBuildingName(apartmentRequest.getBuildName());
                apartmentEntity.setCity(apartmentRequest.getCity());
                apartmentEntity.setDescription(apartmentRequest.getDescription());
                apartmentEntity.setPostalCode(apartmentRequest.getPostalCode());
                apartmentEntity.setRentPrice(apartmentRequest.getRentPrice());
                apartmentEntity.setStreetAddress(apartmentRequest.getStreetAddress());

                clientApartmentEntities.add(apartmentEntity);
            }

            clientEntity.setApartments(clientApartmentEntities);
        }
        return clientRepository.save(clientEntity).getClientId();
    }
}