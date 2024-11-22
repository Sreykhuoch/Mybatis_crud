package com.example.mybatis_crud.repository;

import com.example.mybatis_crud.model.Attendees;
import com.example.mybatis_crud.model.Events;
import com.example.mybatis_crud.model.dto.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            //map one to one relation
            @Result(property = "venue",  column = "venue_id",
            one = @One(select = "com.example.mybatis_crud.repository.VenueRepository.getVenueById")),  // we find venue by venue id
            @Result(property = "attendeesList", column = "event_id",
            many = @Many(select = "getAllAttendeeByEventId"))
            // retrieves list of attendees object based on the event id column
    })
    @Select("""
        select * from events;
""")
    List<Events> getAllEvent();



    // we need to join between attendees table with event_attendee table to fetch attendee base on the event_id
    @Results(id = "attendeeMapping", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
     select 
         at.attendee_id,
         at.attendee_name,
               at.email
        from attendees at
        join event_attendee ea on at.attendee_id = ea.attendee_id
        where event_id = #{id}
""")
    List<Attendees> getAllAttendeeByEventId(@Param("id") int eventId);


    @Select("select * from events where event_id=#{id}")
    @ResultMap("eventMapper")
    Events getEventById(@Param("id") Integer id);


    @Select("""
       INSERT INTO events(event_name, event_date, venue_id)
                    values(#{event.eventName}, #{event.eventDate}, #{event.venueId})    
       RETURNING *
""")
    @ResultMap("eventMapper")
    Events createEvent(@Param("event") EventRequest eventRequest);


    //insert attendee id and event id to middle table
    @Select("""
     INSERT INTO event_attendee(event_id, attendee_id)
     values(#{eventId}, #{attendeeId})
""")
    @ResultMap("eventMapper")
    void insertAttendeeAndEvent(Integer eventId,Integer attendeeId );


    //update the events
    @Select("""
        update events set event_name = #{event.eventName},
                          event_date=#{event.eventDate},
                          venue_id=#{event.venueId}
        where event_id=#{id}
        RETURNING *
    """)
    @ResultMap("eventMapper")
    Events updateEvent(@Param("id") Integer id, @Param("event") EventRequest eventRequest);

    // delete all attendee by event id

    @Delete("""
        delete from event_attendee where event_id=#{eventId}
""")
    void deleteAllAttendeeByEventId(@Param("eventId") Integer eventId);

    @Delete(
            """
     delete from events where event_id =#{id}
     """
    )

    void deleteEventById(@Param("id") Integer id);
}
