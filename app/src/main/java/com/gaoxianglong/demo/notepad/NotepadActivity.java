package com.gaoxianglong.demo.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gaoxianglong.demo.App;
import com.gaoxianglong.demo.R;
import com.google.android.material.navigation.NavigationView;

public class NotepadActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
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
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_more);
        }
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
                Toast.makeText(App.content,"添加",Toast.LENGTH_SHORT).show();
                break;
            case R.id.notepad_delete:
                Toast.makeText(App.content,"删除",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}