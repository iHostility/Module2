package com.hostile.module2.dto.airports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AirportDTO {
    private String name;
    private String iata;
}
