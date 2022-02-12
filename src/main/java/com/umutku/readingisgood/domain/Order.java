package com.umutku.readingisgood.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "customer_order")
@Entity
@NoArgsConstructor
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;

    @Column(name = "order_date")
    private Date date;

    public Order(Customer customer, List<Book> books) {
        this.customer = customer;
        this.books = books;
        this.date = Date.from(Instant.now());
    }

}
