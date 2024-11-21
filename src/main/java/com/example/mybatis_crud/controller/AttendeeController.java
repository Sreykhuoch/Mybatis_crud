package com.example.mybatis_crud.controller;

import com.example.mybatis_crud.model.Attendees;
import com.example.mybatis_crud.model.dto.request.AttendeeRequest;
import com.example.mybatis_crud.model.response.ApiResponse;
import com.example.mybatis_crud.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendees>>> getAllAttendees(){
        List<Attendees> attendees = attendeeService.getAllAttendees();
        ApiResponse<List<Attendees>> apiResponse = ApiResponse.<List<Attendees>>builder()
                .message("Successfully fetched all attendees")
                .payload(attendees)
                .status(HttpStatus.OK)  // Using status OK explicitly
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createAttendee(@RequestBody AttendeeRequest attendeeRequest){
        Attendees attendees = attendeeService.createAttendee(attendeeRequest);
        ApiResponse<Attendees> apiResponse = ApiResponse.<Attendees>builder()
                .message("Successfully create attendees")
                .payload(attendees)
                .status(HttpStatus.CREATED)  // Using status OK explicitly
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendeeById(@PathVariable Integer id){
        Attendees attendees = attendeeService.getAttendeeById(id);
        ApiResponse<Attendees> apiResponse = ApiResponse.<Attendees>builder()
                .message("Successfully get attendees by Id")
                .payload(attendees)
                .status(HttpStatus.OK)  // Using status OK explicitly
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendeeById(@PathVariable Integer id, @RequestBody AttendeeRequest attendeeRequest){
        Attendees attendees = attendeeService.updateAttendee(id, attendeeRequest);
        ApiResponse<Attendees> apiResponse = ApiResponse.<Attendees>builder()
                .message("Successfully update attendees")
                .payload(attendees)
                .status(HttpStatus.OK)  // Using status OK explicitly
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendeeById(@PathVariable Integer id){
         attendeeService.deleteAttendee(id);
        ApiResponse<Attendees> apiResponse = ApiResponse.<Attendees>builder()
                .message("Successfully delete attendees")
                .payload(null)
                .status(HttpStatus.OK)  // Using status OK explicitly
                .localDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
