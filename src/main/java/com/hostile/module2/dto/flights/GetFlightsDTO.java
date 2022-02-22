package com.hostile.module2.dto.flights;

import com.hostile.module2.errors.CustomApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@Data @AllArgsConstructor
public class GetFlightsDTO {
    @Pattern(regexp = "[A-Z]{3}", message = "Must be valid IATA code")
    private String from;
    @Pattern(regexp = "[A-Z]{3}", message = "Must be valid IATA code")
    private String to;

    private String date1;
    private String date2;

    @Size(min = 1, max = 8, message = "Must be a number between 1 and 8")
    @Nullable
    private Integer passengers;

    public static void dateValidator(CharSequence text){
        try {
            LocalDate.parse(text);
        } catch (DateTimeParseException e){
            Map<String, String> err = new HashMap<>();
            err.put("date", "Date field has invalid value");
            throw new CustomApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Validation error ", err);
        }
    }

    public static void date1BeforeDate2(LocalDate from, LocalDate after){
        if (from.isAfter(after)) {
            Map<String, String> err = new HashMap<>();
            err.put("date", "Date2 must be after date 1");
            throw new CustomApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Validation error ", err);
        }
    }
}
