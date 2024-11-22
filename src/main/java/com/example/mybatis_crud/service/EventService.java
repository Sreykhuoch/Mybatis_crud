package com.example.mybatis_crud.service;

import com.example.mybatis_crud.model.Events;
import com.example.mybatis_crud.model.dto.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents();

    Events getEventById(Integer id);

    Events createEvent(EventRequest eventRequest);

    Events updateEvent(Integer id, EventRequest eventRequest);

    void deleteEvent(Integer id);
}
