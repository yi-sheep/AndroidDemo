package com.gaoxianglong.demo;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.gaoxianglong.demo.notepad.database.EventDao;
import com.gaoxianglong.demo.notepad.database.EventDataBase;

public class App extends Application {
    public static String EVENT_KEY_NAME = "EVENT_KEY_NAME";
    public static String EVENT_KEY_TITLE = "EVENT_KEY_TITLE";
    public static String EVENT_KEY_CONTENT = "EVENT_KEY_CONTENT";
    public static String EVENT_KEY_ID = "EVENT_KEY_ID";
    public static String EVENT_KEY_DATE = "EVENT_KEY_DATE";
    public static Context content;
    private static EventDao events;
    @Override
    public void onCreate() {
        super.onCreate();
        content = getApplicationContext();
        events = Room.databaseBuilder(content, EventDataBase.class, "events").addMigrations().build().eventDao();
    }

    public static EventDao getEvents() {
        return events;
    }
}
