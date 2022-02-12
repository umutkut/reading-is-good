package com.umutku.readingisgood.infrastructure;

import com.umutku.readingisgood.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDateAfterAndDateBefore(Date startDate, Date endDate);
}
