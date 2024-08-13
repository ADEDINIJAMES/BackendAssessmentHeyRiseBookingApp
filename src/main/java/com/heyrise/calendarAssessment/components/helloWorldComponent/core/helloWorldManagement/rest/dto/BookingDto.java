package com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
