package com.example.mybatis_crud.controller;

import com.example.mybatis_crud.model.Venue;
import com.example.mybatis_crud.model.dto.request.VenueRequest;
import com.example.mybatis_crud.model.response.ApiResponse;
import com.example.mybatis_crud.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venue")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(){
        List<Venue> venue = venueService.getAllVenue();
        ApiResponse<List<Venue>> apiResponse = ApiResponse.<List<Venue>>builder()
                .message("get all venue successfully ")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVenueById(@PathVariable Integer id)
    {
        Venue venue = venueService.getVenueById(id);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("get venue by id successfully ")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }


    @PostMapping
    public ResponseEntity<?> postVenue(@RequestBody VenueRequest venueRequest){
        Venue venue = venueService.createVenue(venueRequest);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("get venue by id successfully ")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenue(@PathVariable Integer id, @RequestBody VenueRequest venueRequest){
        Venue venue = venueService.updateVenueById(id, venueRequest);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("update venue by id successfully ")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenue(@PathVariable Integer id){
        venueService.deleteVenueById(id);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("delete venue by id successfully ")
                .payload(null)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }

}
