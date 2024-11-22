package com.example.mybatis_crud.service.serviceImpl;

import com.example.mybatis_crud.model.Events;
import com.example.mybatis_crud.model.dto.request.EventRequest;
import com.example.mybatis_crud.repository.EventRepository;
import com.example.mybatis_crud.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Events> getAllEvents() {
        return eventRepository.getAllEvent();
    }

    @Override
    public Events getEventById(Integer id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public Events createEvent(EventRequest eventRequest) {
        Events events = eventRepository.createEvent(eventRequest);

        // retrieve attendee id in each object
        for(Integer attendeeId : eventRequest.getAttendeesId()){
            eventRepository.insertAttendeeAndEvent(events.getEventId(), attendeeId);
        }
        return getEventById(events.getEventId());
    }

    @Override
    public Events updateEvent(Integer id, EventRequest eventRequest) {

        // first we  update it only (event_name, event_date and venue_id)
        Events events = eventRepository.updateEvent(id, eventRequest);

        //if id not found, make it throw exception
        if(events == null){
            throw new IllegalArgumentException("id is not found !! ");
        }
        //delete attendee from the old record
        eventRepository.deleteAllAttendeeByEventId(id);

        //insert the record one by we have to loop through each object

        for(Integer attendeeId: eventRequest.getAttendeesId()){
            eventRepository.insertAttendeeAndEvent(events.getEventId(), attendeeId);
        }

        // return each object, with specific id

        return getEventById(events.getEventId());
    }

    @Override
    public void deleteEvent(Integer id) {
        eventRepository.deleteEventById(id);
    }
}
