package com.gaoxianglong.demo.notepad.ui;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gaoxianglong.demo.App;
import com.gaoxianglong.demo.R;
import com.gaoxianglong.demo.notepad.database.Event;

import java.util.Date;

public class EventRecyclerAdapter extends ListAdapter<Event,EventRecyclerAdapter.ViewHolder> {
    private Context context;
    public EventRecyclerAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = getItem(position);
        holder.title.setText(event.getEventTitle());
        holder.content.setText(event.getEventContent());
        holder.date.setText(formatData(Long.parseLong(event.getCreateDate())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditEventActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(App.EVENT_KEY_TITLE,event.getEventTitle());
                bundle.putString(App.EVENT_KEY_CONTENT,event.getEventContent());
                bundle.putString(App.EVENT_KEY_DATE,event.getCreateDate());
                bundle.putInt(App.EVENT_KEY_ID,event.getId());
                intent.putExtra(App.EVENT_KEY_NAME,bundle);
                context.startActivity(intent);
            }
        });
    }

    public static String formatData(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeStamp);
        return simpleDateFormat.format(date);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.event_title);
            content = itemView.findViewById(R.id.event_content);
            date = itemView.findViewById(R.id.event_date);
        }
    }
}
