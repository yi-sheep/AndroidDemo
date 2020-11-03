package com.gaoxianglong.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaoxianglong.demo.listView.MainActivity
import com.gaoxianglong.demo.news.NewsFragmentActivity
import com.gaoxianglong.demo.notepad.NotepadActivity
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        list_btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        news_btn.setOnClickListener {
            startActivity(Intent(this,NewsFragmentActivity::class.java))
        }
        notepad_btn.setOnClickListener {
            startActivity(Intent(this,NotepadActivity::class.java))
        }
    }
}