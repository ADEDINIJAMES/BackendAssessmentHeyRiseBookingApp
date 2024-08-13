package com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.repository;

import com.heyrise.calendarAssessment.components.helloWorldComponent.core.helloWorldManagement.database.entity.Calendars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository  extends JpaRepository<Calendars,Long> {
}
