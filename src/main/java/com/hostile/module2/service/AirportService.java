package com.hostile.module2.service;

import com.hostile.module2.dto.airports.AirportDTO;
import com.hostile.module2.dto.airports.DataDTO;

import java.util.List;

public interface AirportService {
    public DataDTO getAirports(String search);
}
