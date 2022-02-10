package com.umutku.readingisgood.domain;

import com.google.common.base.Preconditions;
import com.umutku.readingisgood.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "order")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    private long customerId;

    @OneToMany
    @JoinColumn(name = "books")
    private List<Book> books;

    private Date date;
}
