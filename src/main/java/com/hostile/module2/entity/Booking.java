package com.hostile.module2.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_from")
    @JoinColumn(table = "flights")
    private Long flightFrom;

    @Column(name = "flight_back")
    @JoinColumn(table = "flights")
    private Long flightBack;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_back")
    private Date dateBack;

    private String code;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;
}
