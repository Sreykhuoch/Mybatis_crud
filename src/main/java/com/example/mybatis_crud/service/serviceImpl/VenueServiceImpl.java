package com.example.mybatis_crud.service.serviceImpl;

import com.example.mybatis_crud.model.Venue;
import com.example.mybatis_crud.model.dto.request.VenueRequest;
import com.example.mybatis_crud.repository.VenueRepository;
import com.example.mybatis_crud.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenue() {
        return venueRepository.getAllVenues();
    }

    @Override
    public Venue getVenueById(Integer id) {
        return venueRepository.getVenueById(id);
    }

    @Override
    public Venue createVenue(VenueRequest venueRequest) {
        return venueRepository.createVenue(venueRequest);
    }

    @Override
    public Venue updateVenueById(Integer id, VenueRequest venueRequest) {
        Venue venue = venueRepository.getVenueById(id);

        if(venue != null){
            venueRepository.updateVenue(id, venueRequest);
        }else {
            throw new IllegalArgumentException("id is not found");
        }

        return venue;
    }

    @Override
    public void deleteVenueById(Integer id) {
        Venue venue = venueRepository.getVenueById(id);

        if(venue != null){
            venueRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("id is not found");
        }

    }
}
