package com.example.mybatis_crud.controller;

import com.example.mybatis_crud.model.Events;
import com.example.mybatis_crud.model.dto.request.EventRequest;
import com.example.mybatis_crud.model.response.ApiResponse;
import com.example.mybatis_crud.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Events>>> getAllEvents(){
        List<Events> eventsList = eventService.getAllEvents();
        ApiResponse<List<Events>> apiResponse = ApiResponse.<List<Events>>builder()
                .message("get all events successfully")
                .payload(eventsList)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id){
        Events events = eventService.getEventById(id);
        ApiResponse<Events> apiResponse = ApiResponse.<Events>builder()
                .message("get events by successfully")
                .payload(events)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody EventRequest eventRequest){
        Events events = eventService.createEvent(eventRequest);
        ApiResponse<Events> apiResponse = ApiResponse.<Events>builder()
                .message("get all events successfully")
                .payload(events)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer id, @RequestBody EventRequest eventRequest){
        Events events =  eventService.updateEvent(id, eventRequest);
        ApiResponse<Events> apiResponse = ApiResponse.<Events>builder()
                .message(" update events successfully")
                .payload(events)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id){
         eventService.deleteEvent(id);
        ApiResponse<Events> apiResponse = ApiResponse.<Events>builder()
                .message(" update events successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


}
