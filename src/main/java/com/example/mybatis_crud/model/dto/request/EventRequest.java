package com.example.mybatis_crud.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private int eventId;
    private String eventName;
    private LocalDate eventDate;
    private Integer venueId;
    private List<Integer> attendeesId;
}
