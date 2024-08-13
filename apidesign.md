Endpoints
Booking Endpoints

POST /api/v1/booking/{calendarId}

Description: Create a new booking.
Request Body: BookingDto (date, startTime, endTime).
Response: BookingResource (id, date, startTime, endTime, calendarId, createdAt, updatedAt).
GET /api/v1/booking/{bookingId}

Description: Get a booking by its ID.
Response: BookingResource.
PUT /api/v1/booking/{bookingId}

Description: Update an existing booking.
Request Body: BookingDto.
Response: String (success message).
DELETE /api/v1/booking/{bookingId}

Description: Delete a booking.
Response: String (success message).
GET /api/v1/booking/timeframes

Description: Calculate available booking timeframes for a specific date.
Query Params: date, duration.
Response: List<TimeFrame> (startTime, endTime).
Calendar Endpoints (Optional for additional requirements)

POST /api/v1/calendar

Description: Create a new calendar.
Request Body: CalendarDto (name).
Response: CalendarResource (id, name, createdAt, updatedAt).
GET /api/v1/calendar/{calendarId}

Description: Get a calendar by its ID.
Response: CalendarResource.
PUT /api/v1/calendar/{calendarId}

Description: Update an existing calendar.
Request Body: CalendarDto.
Response: String (success message).
DELETE /api/v1/calendar/{calendarId}

Description: Delete a calendar.
Response: String (success message).