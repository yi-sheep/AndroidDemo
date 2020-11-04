package com.gaoxianglong.demo.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gaoxianglong.demo.App;
import com.gaoxianglong.demo.R;
import com.gaoxianglong.demo.notepad.database.Event;
import com.gaoxianglong.demo.notepad.ui.EditEventActivity;
import com.gaoxianglong.demo.notepad.ui.EventRecyclerAdapter;
import com.gaoxianglong.demo.notepad.ui.NotepadViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class NotepadActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NotepadViewModel viewModel;
    private RecyclerView recyclerView;
    private EventRecyclerAdapter adapter;
    private FloatingActionButton addEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        viewModel = new ViewModelProvider(this).get(NotepadViewModel.class);
        initView();
        initEvent();
    }

    private void initEvent() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.left_call:
                        Toast.makeText(App.content,"电话",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.left_friends:
                        Toast.makeText(App.content,"朋友",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.left_location:
                        Toast.makeText(App.content,"地址",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.left_mail:
                        Toast.makeText(App.content,"邮件",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.left_tasks:
                        Toast.makeText(App.content,"任务",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        viewModel.getEvent();
        viewModel.eventLive.observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                adapter.submitList(events);
            }
        });
        addEvent.setOnClickListener(v -> {
            startActivity(new Intent(this, EditEventActivity.class));
        });
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        recyclerView = findViewById(R.id.event_recyler);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_more);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        addEvent = findViewById(R.id.add_event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notepad_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.notepad_add:
                startActivity(new Intent(this, EditEventActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModel.getEvent();
    }
}