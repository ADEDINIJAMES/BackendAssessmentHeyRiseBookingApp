package com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.repository;

import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.BookingManager;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.entity.Booking;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.entity.Calendars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.spi.LocaleNameProvider;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCalendarAndDateAndStartTimeLessThanAndEndTimeGreaterThan(Calendars calendar, LocalDate date, LocalTime endTime, LocalTime startTime);
    List<Booking> findByDate(LocalDate date);
}
//bookingRepository.existsByCalendarIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
//        booking.getCalendarId(),
//        booking.getDate(),
//        booking.getEndTime(),
//        booking.getStartTime()
//        );
