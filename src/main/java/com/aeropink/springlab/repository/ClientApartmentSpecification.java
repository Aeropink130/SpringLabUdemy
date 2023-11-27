package com.aeropink.springlab.repository;

import com.aeropink.springlab.entity.ClientApartment;
import org.springframework.data.jpa.domain.Specification;

public class ClientApartmentSpecification {

    public static Specification<ClientApartment> cityContainsIgnoreCase(String keyword){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("city")),
                        "%" + keyword.toLowerCase() + "%"
                );
    }

    public static Specification<ClientApartment> rentPriceBetween(Integer minPrice, Integer maxPrice){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(
                        root.get("rentPrice"),
                        minPrice,maxPrice
                );
    }

    public static Specification<ClientApartment> isAvailableForRent(Boolean isAvailable){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("isAvailableForRent"),
                        isAvailable
                );
    }
}
