package com.hostile.module2.dto.flights;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FlightDTO {
    private AirportDTO from;
    private AirportDTO to;
    private Integer cost;
    private Integer availability;
}
