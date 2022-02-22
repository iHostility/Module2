package com.hostile.module2.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostile.module2.dto.flights.GetFlightsDTO;
import com.hostile.module2.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@RequiredArgsConstructor
public class FlightController {
    private final AirportService airportService;
    private final ObjectMapper om;

    @GetMapping("/airport")
    public ResponseEntity<String> getAirports(@RequestParam(value = "query", required = false) String query) throws JsonProcessingException {
        return ResponseEntity.ok(om.writeValueAsString(airportService.getAirports(query)));
    }

    @GetMapping("/flight")
    public ResponseEntity<String> getFlights(@RequestParam(value = "from") String from,
                                             @RequestParam(value = "to") String to,
                                             @RequestParam(value = "date1") String date1,
                                             @RequestParam(value = "date2") String date2,
                                             @RequestParam(value = "passengers") Integer passengers) throws JsonProcessingException {
        GetFlightsDTO.dateValidator(date1);
        GetFlightsDTO dto;
        if (!date2.isBlank()) {
            GetFlightsDTO.dateValidator(date2);
            GetFlightsDTO.date1BeforeDate2(LocalDate.parse(date1), LocalDate.parse(date2));
            dto = new GetFlightsDTO(from.toUpperCase(),
                    to.toUpperCase(),
                    date1,
                    date2,
                    passengers);

        } else {
            dto = new GetFlightsDTO(from.toUpperCase(),
                    to.toUpperCase(),
                    date1,
                    "",
                    passengers);

        }
        return new ResponseEntity<>(om.writeValueAsString(dto), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/booking")
    public ResponseEntity<String> makeBooking() {
        return ResponseEntity.status(HttpStatus.CONTINUE).build();
    }

    @GetMapping("/booking/{code}")
    public ResponseEntity<String> getBookingInfo() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/booking/{code}/seat")
    public ResponseEntity<String> getFlightSeat() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/booking/{code}/seat")
    public ResponseEntity<String> changeFlightSeat() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
