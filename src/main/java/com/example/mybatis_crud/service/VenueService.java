package com.example.mybatis_crud.service;

import com.example.mybatis_crud.model.Venue;
import com.example.mybatis_crud.model.dto.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenue();

    Venue getVenueById(Integer id);

    Venue createVenue(VenueRequest venueRequest);

    Venue updateVenueById(Integer id, VenueRequest venueRequest);

    void deleteVenueById(Integer id);
}
