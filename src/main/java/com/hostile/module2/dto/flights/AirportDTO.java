package com.hostile.module2.dto.flights;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class AirportDTO {
    private String city;
    private String airport;
    private String iata;
    private LocalDate date;
    private LocalDateTime time;

}
