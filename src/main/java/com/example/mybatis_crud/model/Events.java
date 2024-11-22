package com.example.mybatis_crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private int eventId;
    private String eventName;
    private String eventDate;
    private Venue venue;
    private List<Attendees> attendeesList;
}
