package com.aeropink.springlab.controller;

import com.aeropink.springlab.entity.Client;
import com.aeropink.springlab.entity.ClientApartment;
import com.aeropink.springlab.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ApartmentRestController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping(value = {"/api/clients", "/api/clients/{optionalClientId}"})
    List<Client> getAllClients(
            @PathVariable(required = false, name = "optionalClientId") String optionalCLientId
    ) {
        return apartmentService.findClients(optionalCLientId);
    }

    @GetMapping(value = "/api/apartments")
    List<ClientApartment> getAllApartments(
            @RequestParam(required = false, name = "city") String city,
            @RequestParam(required = false, name = "minPrice") Integer minPrice,
            @RequestParam(required = false, name = "maxPrice") Integer maxPrice,
            @RequestParam(required = false, name = "isAvailableForRent") Boolean isAvailableForRent
    ) {
        return apartmentService.findApartments(city,minPrice,maxPrice,isAvailableForRent);
    }

    @PostMapping(value = "/api/client")
    String createClient(@RequestBody ClientRequest clientRequest){
        return apartmentService.createClient(clientRequest).toString();
    }
}
