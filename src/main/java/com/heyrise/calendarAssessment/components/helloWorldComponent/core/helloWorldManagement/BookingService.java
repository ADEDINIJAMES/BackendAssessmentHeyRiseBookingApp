package com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement;

import com.heyrise.calendarAssessment.common.restException.HeyRiseResourceNotFoundException;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto.BookingDto;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto.TimeFrame;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.resource.BookingResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private BookingManager bookingManager;

    public BookingResource createBooking (BookingDto bookingDto, Long calendarId) {

        return bookingManager.createBooking(bookingDto, calendarId);
    }

    public BookingResource getBooking (Long bookingId){
        return bookingManager.getBooking(bookingId);
    }
    public String updateResource (Long bookingId, BookingDto bookingDto) {
    return bookingManager.updateResource(bookingId,bookingDto);
    }
    public String deleteBooking (Long bookingId) {
        return bookingManager.deleteBooking(bookingId);
    }

    public List<TimeFrame> getBookingTimeFrame (LocalDate date, int duration) {
    return bookingManager.getBookingTimeFrame(date,duration);
    }

    }
