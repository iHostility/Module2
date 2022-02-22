package com.hostile.module2.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@Table(name = "flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_code")
    @Max(10)
    private String flightCode;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Airport fromId;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Airport toId;

    private Integer cost;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;
}
