package com.gaoxianglong.demo.notepad.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoxianglong.demo.App;
import com.gaoxianglong.demo.R;
import com.gaoxianglong.demo.notepad.database.Event;

import java.util.Date;

public class EditEventActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private NotepadViewModel viewModel;
    private boolean isUpdate = false;
    private int id;
    private String createDate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        initView();
        initEvent();
    }

    private void initEvent() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }
        String titleText = "";
        String contentText = "";
        Bundle bundle = getIntent().getBundleExtra(App.EVENT_KEY_NAME);
        if (bundle != null) {
            titleText = bundle.getString(App.EVENT_KEY_TITLE);
            contentText = bundle.getString(App.EVENT_KEY_CONTENT);
            id = bundle.getInt(App.EVENT_KEY_ID);
            createDate = bundle.getString(App.EVENT_KEY_DATE);
            isUpdate = true;
        }

        title.setText(titleText);
        content.setText(contentText);
    }

    private void initView() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        viewModel = new ViewModelProvider(this).get(NotepadViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.complete:
                String title = this.title.getText().toString();
                String content = this.content.getText().toString();
                String time = String.valueOf(new Date().getTime());
                if (isUpdate) {
                    Event event = new Event(id,createDate,time,title,content);
                    viewModel.updateEvent(event);
                }else {
                    Event event = new Event(time,time,title,content);
                    viewModel.addEvent(event);
                }
                finish();
                break;
            case R.id.delete:
                if (isUpdate) {
                    showDialog();
                }else {
                    Toast.makeText(App.content,"该事件未添加!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.drawable.ic_delete);
        dialog.setTitle("通知");
        dialog.setMessage("是否删除!");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Event event = new Event(id);
                viewModel.deleteEvent(event);
                finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}