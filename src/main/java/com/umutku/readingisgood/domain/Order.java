package com.umutku.readingisgood.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "order")
@Entity
@NoArgsConstructor
public class Order extends BaseEntity {

    private long customerId;

    @OneToMany
    @JoinColumn(name = "books")
    private List<Book> books;

    private Date date;

    public Order(long customerId, List<Book> books) {
        this.customerId = customerId;
        this.books = books;
        this.date = Date.from(Instant.now());
    }
}
