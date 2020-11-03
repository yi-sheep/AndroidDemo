package com.gaoxianglong.demo.listView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gaoxianglong.demo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 这里使用listOf()去定义一串集合 arrayOf()是定义一个数组
    private val data = listOf("Apple","高祥龙","201810340134") // 数据
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data) // 实例化一个适配器
        initFruit() // 初始化数据
        val adapter = FruitAdapter(this, R.layout.fruit_item,fruitList) // 使用我们自己的适配器
        list_view.adapter = adapter // 设置适配器
        list_view.setOnItemClickListener { parent, view, position, id ->
            val fruit = fruitList[position] // 获取到点击位置的Fruit实例
            Intent(this, ImageActivity::class.java).apply {
                putExtra("img",fruitList[position].imageId)
                putExtra("name",fruitList[position].name)
                startActivity(this)
            }
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruit() {
        repeat(10){
            fruitList.add(Fruit("Apple", R.drawable.apple_pic,"高祥龙","201810340134"))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic,"吴兴平","201810340136"))
            fruitList.add(Fruit("Orange", R.drawable.orange_pic,"胡建豪","201810340140"))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic,"曾学良","201810340135"))
            fruitList.add(Fruit("Pear", R.drawable.pear_pic,"余春河","201810340137"))
            fruitList.add(Fruit("Grape", R.drawable.grape_pic,"高祥龙","201810340134"))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic,"吴兴平","201810340136"))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic,"胡建豪","201810340140"))
            fruitList.add(Fruit("cherry", R.drawable.cherry_pic,"曾学良","201810340135"))
            fruitList.add(Fruit("mango", R.drawable.mango_pic,"余春河","201810340137"))
            fruitList.add(Fruit("my", R.drawable.my_pic,"高祥龙","201810340134"))
        }
    }
}
