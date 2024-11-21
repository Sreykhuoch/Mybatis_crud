package com.example.mybatis_crud.repository;

import com.example.mybatis_crud.model.Venue;
import com.example.mybatis_crud.model.dto.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository
{
    @Select("select * from venues")
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    List<Venue> getAllVenues();


    @Select("select * from venues where venue_id =#{id};")
    @ResultMap("venueMapper")
    Venue getVenueById(@Param("id") Integer id);


    @Select("""
    insert into venues (venue_name, location) values (#{venue.venueName}, #{venue.location})
    RETURNING *
    """)
    @ResultMap("venueMapper")
    Venue createVenue(@Param("venue") VenueRequest venueRequest);


    @Select("""
     update venues set venue_name=#{venue.venueName}, location=#{venue.location} 
                   where venue_id=#{id}
""")
    @ResultMap("venueMapper")
    Venue updateVenue(@Param("id") Integer id, @Param("venue") VenueRequest venueRequest);

    @Select("delete from venues where venue_id = #{id}")
    void deleteById(@Param("id") Integer id);
}
