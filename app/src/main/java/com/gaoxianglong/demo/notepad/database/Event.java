package com.gaoxianglong.demo.notepad.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "CREADE")
    private String createDate;
    @ColumnInfo(name = "UPDATEDE")
    private String updateDate;
    @ColumnInfo(name = "TITLE")
    private String eventTitle;
    @ColumnInfo(name = "CONTENT")
    private String eventContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                Objects.equals(createDate, event.createDate) &&
                Objects.equals(updateDate, event.updateDate) &&
                Objects.equals(eventTitle, event.eventTitle) &&
                Objects.equals(eventContent, event.eventContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, updateDate, eventTitle, eventContent);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", eventTitle='" + eventTitle + '\'' +
                ", eventContent='" + eventContent + '\'' +
                '}';
    }

    public Event(String createDate, String updateDate, String eventTitle, String eventContent) {
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
    }
    @Ignore
    public Event(int id, String createDate, String updateDate, String eventTitle, String eventContent) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
    }
    @Ignore
    public Event(int id) {
        this.id = id;
    }
}
