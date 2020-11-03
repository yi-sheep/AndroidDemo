package com.gaoxianglong.demo.listView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaoxianglong.demo.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val name = intent.getStringExtra("name")
        val img = intent.getIntExtra("img",0)
        image.setImageResource(img)
        img_name.text = name
    }
}