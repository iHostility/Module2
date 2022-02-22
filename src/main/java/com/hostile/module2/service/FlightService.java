package com.hostile.module2.service;

import com.hostile.module2.dto.flights.DataDTO;
import com.hostile.module2.dto.flights.GetFlightsDTO;

public interface FlightService {
    DataDTO getFlights(GetFlightsDTO dto);
}
