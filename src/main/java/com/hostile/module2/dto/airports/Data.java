package com.hostile.module2.dto.airports;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data @AllArgsConstructor @NoArgsConstructor
public class Data {
    private List<AirportDTO> items;
}
