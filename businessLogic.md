Idea of the Business Logic for the Calendar Booking Application
1. Business Requirements Overview:
   Booking Management: Users need to book time slots for a specific calendar (e.g., a meeting room, doctor appointment, etc.).
   Conflict Handling: The system must prevent double booking by checking for overlapping bookings.
   Availability Check: Users should be able to query available time slots for a specific date and duration.
   CRUD Operations: Users need to be able to create, update, retrieve, and delete bookings.
   User Notifications: Users should be informed if their booking attempt conflicts with an existing booking.
2. Entities and Relationships:
   Calendar: Represents a resource or entity for which bookings can be made (e.g., meeting room, appointment slot).
   Booking: Represents a time slot booked on a specific calendar, with a start time, end time, and date.
3. Core Business Logic:
   Create Booking:

Input: calendarId, date, startTime, endTime.
Process:
Retrieve the calendar by calendarId.
Check if the booking time overlaps with any existing bookings for that calendar on the given date.
If no conflict, create a new booking.
If conflict, throw a HeyRiseConflictException.
Output: Success (booking details) or failure (conflict error).
Retrieve Booking:

Input: bookingId
Process:
Retrieve the booking details using the bookingId.
If not found, throw a HeyRiseResourceNotFoundException.
Output: Booking details
Update Booking:

Input: bookingId, date, startTime, endTime.
Process:
Retrieve the booking by bookingId.
Check if the new booking time overlaps with any existing bookings (excluding the current booking).
If no conflict, update the booking details.
If conflict, throw a HeyRiseConflictException.
Output: Success message or failure (conflict error).
Delete Booking:

Input: bookingId.
Process:
Retrieve the booking by bookingId.
If not found, throw a HeyRiseResourceNotFoundException.
Delete the booking.
Output: Success message.
Check Available Time Frames:

Input: date, duration.
Process:
Retrieve all bookings for the given date.
Calculate available time slots that do not overlap with any existing bookings and fit the requested duration.
Output: List of available time slots.
4. Validation Logic:
   Overlap Validation:
   Ensure no two bookings overlap in time on the same calendar.
   Overlapping scenarios include:
   New booking starts before an existing booking ends.
   New booking ends after an existing booking starts.
   Time Frame Calculation:
   Calculate potential booking slots by identifying gaps between existing bookings.
   Ensure the calculated time slots can accommodate the requested duration.
5. Error Handling:
   Conflict Handling:
   Use HeyRiseConflictException to handle and return a 409 Conflict response when a booking overlaps with an existing one.
   Resource Not Found:
   Use HeyRiseResourceNotFoundException to handle scenarios where a requested booking or calendar does not exist, returning a 404 Not Found response.
6. Scalability and Performance Considerations:
   Indexing: Index calendar_id and date columns to speed up query performance, especially in environments with high booking volumes.
   Batch Processing: For systems with a high volume of bookings, consider batch processing or queuing mechanisms to handle peak loads.
7. User Notifications:
   Notify users immediately if their booking attempt fails due to an overlap.
   Confirm successful bookings with relevant details.