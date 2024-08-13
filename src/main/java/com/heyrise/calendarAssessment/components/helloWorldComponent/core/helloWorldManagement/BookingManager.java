package com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement;

import com.heyrise.calendarAssessment.common.restException.HeyRiseConflictException;
import com.heyrise.calendarAssessment.common.restException.HeyRiseResourceNotFoundException;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.entity.Booking;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.entity.Calendars;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.repository.BookingRepository;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.repository.CalendarRepository;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto.BookingDto;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto.TimeFrame;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.resource.BookingResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingManager {

    private BookingRepository bookingRepository;
    private CalendarRepository calendarRepository;

    public BookingResource createBooking (BookingDto bookingDto, Long calendarId){
        Calendars calendars = calendarRepository.findById(calendarId).orElseThrow(()-> new HeyRiseResourceNotFoundException("Calender not found"));
       boolean overlaps= bookingRepository.existsByCalendarAndDateAndStartTimeLessThanAndEndTimeGreaterThan(calendars,bookingDto.getDate(),bookingDto.getEndTime(),bookingDto.getStartTime());
        if(!overlaps) {
            Booking booking = bookingRepository.save(Booking.builder()
                    .date(bookingDto.getDate())
                    .endTime(bookingDto.getEndTime())
                    .startTime(bookingDto.getStartTime())
                    .calendar(calendars)
                    .build()
            );
            return BookingResource.builder()
                    .createdAt(booking.getCreatedAt())
                    .id(booking.getId())
                    .calendarId(booking.getCalendar().getId())
                    .endTime(booking.getEndTime())
                    .startTime(booking.getStartTime())
                    .updatedAt(booking.getUpdatedAt())
                    .date(booking.getDate())
                    .build();
        }
        throw new HeyRiseConflictException("Booking Date already picked", "409");
    }


    public BookingResource getBooking (Long bookingId){
       Booking booking= bookingRepository.findById(bookingId).orElseThrow(()-> new HeyRiseResourceNotFoundException("booking not found "));
        return BookingResource.builder()
                .createdAt(booking.getCreatedAt())
                .id(booking.getId())
                .calendarId(booking.getCalendar().getId())
                .endTime(booking.getEndTime())
                .startTime(booking.getStartTime())
                .updatedAt(booking.getUpdatedAt())
                .date(booking.getDate())
                .build();
    }

    public String updateResource (Long bookingId, BookingDto bookingDto){

        Booking booking= bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("booking not found "));
        boolean overlaps= bookingRepository.existsByCalendarAndDateAndStartTimeLessThanAndEndTimeGreaterThan(booking.getCalendar(),bookingDto.getDate(),bookingDto.getEndTime(),bookingDto.getStartTime());
        if(!overlaps) {
            if (bookingDto != null) {
                booking.setStartTime(bookingDto.getStartTime());
                booking.setDate(bookingDto.getDate());
                booking.setEndTime(bookingDto.getEndTime());
                bookingRepository.save(booking);
                return "Booking updated successfully";
            }
            return "Request is Empty";
        }
        throw new HeyRiseConflictException("Booking Date already picked", "409");

    }

    public String deleteBooking (Long bookingId){
        Booking booking= bookingRepository.findById(bookingId).orElseThrow(()-> new HeyRiseResourceNotFoundException("booking not found "));
        bookingRepository.delete(booking);
        return "booking deleted successfully";
    }

    public List<TimeFrame> getBookingTimeFrame (LocalDate date, int duration){
        List<Booking> bookingList = bookingRepository.findByDate(date);
        List<TimeFrame> timeFrameList = new ArrayList<>();

        for(Booking booking: bookingList){

            if(booking.getStartTime().plusMinutes(duration)==booking.getEndTime()){
              TimeFrame timeFrame=  TimeFrame.builder().startTime(booking.getStartTime())
                        .endTime(booking.getEndTime())
                        .build();
                timeFrameList.add(timeFrame);
            }
        }
        return timeFrameList;
    }
}
