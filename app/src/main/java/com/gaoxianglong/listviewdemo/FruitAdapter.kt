package com.gaoxianglong.listviewdemo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * 类名后面跟的()表示这个类的主构造函数
 * 而基础的类后面跟()表示调用继承类的构造进行实例继承
 * 具体传入什么产生根据需要
 * 我这里在当前类的主构造函数的接收参数中加入了三个
 * 第一个：类型为Activity
 * 第二个：这既是参数也是定义的一个变量，类型为Int
 * 第三个：类型为Fruit的集合
 */
class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resourceId, data) {
    /**
     * 重写的函数
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val viewHolder:ViewHolder
        if (convertView == null) {
            view= LayoutInflater.from(context).inflate(resourceId, parent, false) // 加载item布局
            val fruitImage: ImageView = view.findViewById(R.id.imageView) // 找到image控件
            val fruitName: TextView = view.findViewById(R.id.textView) // 找到text控件
            viewHolder = ViewHolder(fruitImage,fruitName)
            view.tag = viewHolder
        }else{
            view=convertView
            viewHolder = view.tag as ViewHolder
        }
        val item = getItem(position) // 获取当前项的Fruit实例
        // 判断fruit是否为空
        if (item != null) {
            viewHolder.fruitImage.setImageResource(item.imageId)
            viewHolder.fruitName.text = item.name
        }
        return view
    }
    // inner class 是用来定义内部类的
    inner class ViewHolder(val fruitImage:ImageView,val fruitName:TextView)
}