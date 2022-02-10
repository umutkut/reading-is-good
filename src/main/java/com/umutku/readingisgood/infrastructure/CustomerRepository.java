package com.umutku.readingisgood.infrastructure;

import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByUserName(String userName);
}
