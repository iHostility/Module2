package com.hostile.module2.service.impl;

import com.hostile.module2.dto.flights.DataDTO;
import com.hostile.module2.dto.flights.FlightDTO;
import com.hostile.module2.dto.flights.FlightsResponseDTO;
import com.hostile.module2.dto.flights.GetFlightsDTO;
import com.hostile.module2.entity.Airport;
import com.hostile.module2.entity.Flights;
import com.hostile.module2.repo.AirportRepository;
import com.hostile.module2.repo.FlightRepository;
import com.hostile.module2.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepo;
    private final AirportRepository airportRepo;
    private final ModelMapper mm;

    @Override
    public DataDTO getFlights(GetFlightsDTO dto) {
        Airport from = airportRepo.findByIata(dto.getFrom());
        Airport to = airportRepo.findByIata(dto.getTo());
        List<Flights> flights = flightRepo.findByFromIdAndToId(from, to);
        DataDTO response = new DataDTO();
        FlightsResponseDTO flightsResponseDTO = new FlightsResponseDTO();
        List<FlightDTO> flightsDTO = new ArrayList<>();
        flights.forEach(flights1 -> {
            flightsDTO.add(mm.map(flights1, FlightDTO.class));
        });
        flightsResponseDTO.setFlights_to(flightsDTO);

        return null;
    }
}
