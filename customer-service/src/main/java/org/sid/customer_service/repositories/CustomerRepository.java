package com.abdo.customer_service.repositories;

import com.abdo.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;


@RepositoryRestController
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
