# ListViewDemo

从这一期开始都是第一行代码第三版的内容了，大概看了一下前面翻译的代码基本上的正确的，但是有些解释没有到位，还是可以那样理解，但不能就觉得那些内部的原理就是那样的。毕竟是我个人的理解。
这一期就将一下，基本上不使用但是必须知道的控件listView！

拖一个ListView到布局中，然后到活动类中编写如下代码
```kotlin
class MainActivity : AppCompatActivity() {

    // 这里使用listOf()去定义一串集合 arrayOf()是定义一个数组
    private val data = listOf("Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","cherry","mango","Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","cherry","mango") // 数据

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data) // 实例化一个适配器
        list_view.adapter = adapter // 设置适配器
    }
}
```

可以看到我们使用的是Android自带的适配器,一般都是自己写，可以根据自己的需求来定义。

这里我们来创建一个适配器，首先适配器需要一个item的布局文件，在res下的layout文件夹中创建一个布局文件fruit_item.xml.

    这个布局中有一个ImageView和一个TextView,分别用来显示水果的图片和水果的名字。

然后现在还需要一个数据类Fruit用于存放水果的图片和名字
```kotlin
/**
 * 这里使用了一个简便的方式创建一个类并创建了一个构造方法
 * class Fruit ： 定义了一个类
 * (val name:String, val imageId:Int) ：定义了类的构造方法的接收参数
 */
class Fruit (val name:String, val imageId:Int)
```

现在开始创建适配器

```kotlin
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
        
        val view= LayoutInflater.from(context).inflat(resourceId, parent, false) // 加载item布局
        
        val fruitImage: ImageView = view.findViewById(R.id.imageView) // 找到image控件
            val fruitName: TextView = view.findViewById(R.id.textView) // 找到text控件
        val item = getItem(position) // 获取当前项的Fruit实例
        // 判断fruit是否为空
        if (item != null) {
            viewHolder.fruitImage.setImageResource(item.imageId)
            viewHolder.fruitName.text = item.name
        }
        return view
    }
}
```
定义了之后还要使用才行，回到活动类

```kotlin
class MainActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFruit() // 初始化数据
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList) // 使用我们自己的适配器
        list_view.adapter = adapter // 设置适配器
    }

    private fun initFruit() {
        fruitList.add(Fruit("Apple",R.drawable.apple_pic))
        fruitList.add(Fruit("Banana",R.drawable.banana_pic))
        fruitList.add(Fruit("Orange",R.drawable.orange_pic))
        fruitList.add(Fruit("Watermelon",R.drawable.watermelon_pic))
        fruitList.add(Fruit("Pear",R.drawable.pear_pic))
        fruitList.add(Fruit("Grape",R.drawable.grape_pic))
        fruitList.add(Fruit("Pineapple",R.drawable.pineapple_pic))
        fruitList.add(Fruit("Strawberry",R.drawable.strawberry_pic))
        fruitList.add(Fruit("cherry",R.drawable.cherry_pic))
        fruitList.add(Fruit("mango",R.drawable.mango_pic))
        ....
    }
}
```

现在来说说为什么不使用listView,是因为它有很多地方需要优化。这里就不把优化写出来了，demo中做了的，去看看吧！

接下来是它的点击事件
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        ...
        list_view.setOnItemClickListener { parent, view, position, id ->
            val fruit = fruitList[position] // 获取到点击位置的Fruit实例
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }
    }
```

到这里就完了，下一期是recyclerView