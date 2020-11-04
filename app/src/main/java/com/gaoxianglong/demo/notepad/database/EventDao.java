package com.gaoxianglong.demo.notepad.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void addEvent(Event event);

    @Update(entity = Event.class)
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);

    @Query("select * from event ORDER BY UPDATEDE DESC")
    List<Event> viewEvent();
}
