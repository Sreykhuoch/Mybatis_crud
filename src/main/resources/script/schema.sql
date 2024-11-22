create DATABASE mybatis_crud;

create table attendees(
    attendee_id serial primary key ,
    attendee_name Varchar(50),
    email VARCHAR(50) unique
);

create table venues(
    venue_id serial primary key ,
    venue_name varchar(50),
    location varchar(100)
);


create table events(
    event_id serial primary key ,
    event_name varchar(50),
    event_date date,
    venue_id integer references venues(venue_id) on delete cascade
                   on update cascade
);

create table event_attendee(
    id serial primary key ,
    event_id integer references events(event_id) on delete cascade
                           on update cascade ,
    attendee_id integer references attendees(attendee_id) on delete cascade on update cascade
);

insert into attendees (attendee_name , email) values ('khushee', 'veasna123@gmail.com');


update attendees set attendee_name='moni', email='moni@gmail.com'
where attendee_id = 1;

DELETE from attendees  where attendee_id=1;

-- venue :

select * from venues where venue_id =1;

insert into venues (venue_name, location) values ('stocking', 'takeo');

update venues set venue_name='koko', location='bobo' where venue_id=34;



-- retrieve all attendees by eventId

select at.attendee_name,
       at.email
from attendees at
join event_attendee ea on at.attendee_id = ea.attendee_id
where event_id = 1;

select * from events where event_id=3;


update events set event_name = 'noianoia',
                  event_date='2024-11-20',
                  venue_id=1
where event_id=3;





