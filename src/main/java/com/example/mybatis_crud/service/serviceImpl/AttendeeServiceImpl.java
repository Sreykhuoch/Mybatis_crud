package com.example.mybatis_crud.service.serviceImpl;

import com.example.mybatis_crud.model.Attendees;
import com.example.mybatis_crud.model.dto.request.AttendeeRequest;
import com.example.mybatis_crud.repository.AttendeeRepository;
import com.example.mybatis_crud.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendees> getAllAttendees() {
        return attendeeRepository.getAllAttendee();
    }

    @Override
    public Attendees createAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.createAttendee(attendeeRequest);
    }

    @Override
    public Attendees getAttendeeById(Integer id) {
        return attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendees updateAttendee(Integer id, AttendeeRequest attendeeRequest) {
        Attendees attendees = attendeeRepository.getAttendeeById(id);
        if(attendees != null){
            attendeeRepository.updateAttendeeById(id, attendeeRequest);
        }else {
            throw new IllegalArgumentException("id is not exist");
        }
        return attendees;
    }

    @Override
    public void deleteAttendee(Integer id) {
        attendeeRepository.deleteAttendee(id);
    }
}
