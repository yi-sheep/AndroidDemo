package com.gaoxianglong.demo.notepad.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.gaoxianglong.demo.App;
import com.gaoxianglong.demo.notepad.database.Event;

import java.util.List;

public class NotepadViewModel extends ViewModel {
    private MediatorLiveData<List<Event>> _eventLive = new MediatorLiveData<>();
    public LiveData<List<Event>> eventLive = _eventLive;

    public void getEvent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                viewEvent();
            }
        }).start();

    }

    private void viewEvent() {
        List<Event> events = App.getEvents().viewEvent();
        _eventLive.postValue(events);
    }

    public void addEvent(Event event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                App.getEvents().addEvent(event);
                viewEvent();
            }
        }).start();
    }

    public void updateEvent(Event event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                App.getEvents().updateEvent(event);
                Log.d("my", event.toString());
                viewEvent();
            }
        }).start();
    }

    public void deleteEvent(Event event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                App.getEvents().deleteEvent(event);
                viewEvent();
            }
        }).start();
    }
}
