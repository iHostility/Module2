package com.hostile.module2.service.impl;

import com.hostile.module2.dto.airports.AirportDTO;
import com.hostile.module2.dto.airports.Data;
import com.hostile.module2.dto.airports.DataDTO;
import com.hostile.module2.entity.Airport;
import com.hostile.module2.repo.AirportRepository;
import com.hostile.module2.service.AirportService;
import com.hostile.module2.specification.AirportsSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepo;
    private final ModelMapper mm;

    @Override
    public DataDTO getAirports(String search) {
        DataDTO dto = new DataDTO();
        Data data = new Data();
        data.setItems(airportRepo.findAll(new AirportsSpecification(search)).stream().map(this::convertToDTO).collect(Collectors.toList()));
        dto.setData(data);
        return dto;
    }

    private AirportDTO convertToDTO(Airport airport) {
        return mm.map(airport, AirportDTO.class);
    }
}
