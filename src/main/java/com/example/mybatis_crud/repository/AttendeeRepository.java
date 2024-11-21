package com.example.mybatis_crud.repository;

import com.example.mybatis_crud.model.Attendees;
import com.example.mybatis_crud.model.dto.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {


    @Select("select * from attendees")
    @Results(id = "attendeeMap", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    List<Attendees> getAllAttendee();


    @Select("""
       INSERT INTO attendees(attendee_name, email) values (#{attendee.attendeeName}, #{attendee.email})
       RETURNING *
""")
    @ResultMap("attendeeMap")
    Attendees createAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    @Select("select * from attendees where attendee_id = #{id}")
    @ResultMap("attendeeMap")
    Attendees getAttendeeById(@Param("id") Integer id);

    @Select("""
    update attendees set attendee_name= #{attendee.attendeeName}, email=#{attendee.email}
    where attendee_id = #{id};
""")
    @ResultMap("attendeeMap")
    void updateAttendeeById(@Param("id") Integer id, @Param("attendee") AttendeeRequest attendeeRequest);


    @Select("DELETE from attendees  where attendee_id=#{id};")
    void deleteAttendee(@Param("id") Integer id);
}
