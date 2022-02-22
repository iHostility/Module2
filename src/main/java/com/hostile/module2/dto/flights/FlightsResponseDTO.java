package com.hostile.module2.dto.flights;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class FlightsResponseDTO {
    private Long flightID;
    private String flightCode;
    private List<FlightDTO> flights_to;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FlightDTO> flights_back;

}
