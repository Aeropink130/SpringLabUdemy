package com.aeropink.springlab.repository;

import com.aeropink.springlab.entity.ClientApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientApartmentRepository extends JpaRepository<ClientApartment, UUID>, JpaSpecificationExecutor<ClientApartment> {

}