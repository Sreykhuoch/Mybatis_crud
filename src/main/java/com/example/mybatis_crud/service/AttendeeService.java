package com.example.mybatis_crud.service;

import com.example.mybatis_crud.model.Attendees;
import com.example.mybatis_crud.model.dto.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendees> getAllAttendees();

    Attendees createAttendee(AttendeeRequest attendeeRequest);

    Attendees getAttendeeById(Integer id);

    Attendees updateAttendee(Integer id, AttendeeRequest attendeeRequest);

    void deleteAttendee(Integer id);
}
