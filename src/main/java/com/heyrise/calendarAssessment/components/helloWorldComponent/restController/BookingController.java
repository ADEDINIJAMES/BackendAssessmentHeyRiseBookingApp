package com.heyrise.calendarAssessment.components.helloWorldComponent.restController;

import com.heyrise.calendarAssessment.common.restException.HeyRiseConflictException;
import com.heyrise.calendarAssessment.common.restException.HeyRiseResourceNotFoundException;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.BookingService;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto.BookingDto;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.dto.TimeFrame;
import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.rest.resource.BookingResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
    private BookingService bookingService;

@PostMapping("/{calenderId}")

    public ResponseEntity<BookingResource> createBooking(@Valid @RequestBody BookingDto bookingDto, @PathVariable Long calenderId) throws Exception {
    return ResponseEntity.status(201).body(bookingService.createBooking(bookingDto, calenderId));

}
@GetMapping("/{bookingId}")
public ResponseEntity<BookingResource> getBooking (@PathVariable Long bookingId){
    return ResponseEntity.ok(bookingService.getBooking(bookingId));
}
@PutMapping("/{bookingId}")
public ResponseEntity<String> updateBooking (@PathVariable Long bookingId, @Valid @RequestBody BookingDto bookingDto){
    return ResponseEntity.ok(bookingService.updateResource(bookingId, bookingDto));
}
@DeleteMapping("/{bookingId}")
public  ResponseEntity<String> deleteBooking (@PathVariable Long bookingId){
    return ResponseEntity.ok(bookingService.deleteBooking(bookingId));
}

@GetMapping("/timeframes")
    public ResponseEntity<List<TimeFrame>> getBookingTimeFrame (
        @RequestParam (required = false, name = "dateOfBooking") LocalDate date,
        @RequestParam (required = false, name = "duration") int duration
        ){
    return ResponseEntity.ok(bookingService.getBookingTimeFrame(date,duration));
    }
}


