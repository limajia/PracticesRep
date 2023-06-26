package osp.leobert.android.drawableworkshop

import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import osp.leobert.android.drawableworkshop.databinding.ActivityMainBinding
import osp.leobert.android.drawableworkshop.drawable.AnimLetterDrawable2
import osp.leobert.android.drawableworkshop.drawable.LetterDrawable

//https://juejin.cn/post/6924240361317466125
// 案例中分析了AnimationDrawable例子，调用绘制是通过CallBack回调出去，应该是View创建和绘制过程中设置进去的
//class View {
//    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
//        if (verifyDrawable(who) && what != null) {
//            final long delay = when - SystemClock.uptimeMillis();
//            if (mAttachInfo != null) {
//                mAttachInfo.mViewRootImpl.mChoreographer.postCallbackDelayed(
//                        Choreographer.CALLBACK_ANIMATION, what, who,
//                        Choreographer.subtractFrameDelay(delay));
//            } else {
//                // Postpone the runnable until we know
//                // on which thread it needs to run.
//                getRunQueue().postDelayed(what, delay);
//            }
//        }
//    }
//}
// 这个demo中 触发invalidateSelf()，从而触发onDraw()，这样就实现了动画效果

//Drawable中 DrawableInflater XmlPullParser 一起 在运行时解析一个xml文件并回调inflate()方法
class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val letterDrawable: LetterDrawable? = binding.testView.background as LetterDrawable?
        //ContextCompat.getDrawable(this, R.drawable.letter_drawable) as LetterDrawable?

//        binding.testView2.background = letterDrawable

        binding.btnChangeColor.setOnClickListener {
            letterDrawable?.let {
                Log.d(tag, "has callback: ${it.callback == null}")
                it.color = Color.MAGENTA
                it.invalidateSelf()
            }
        }

        binding.btnStartAnim.setOnClickListener { _ ->

            binding.viewAnim.background.let {
                if (it is Animatable) {
                    it.start()
                }
            }
        }

        binding.btnStopAnim.setOnClickListener {
            binding.viewAnim.background.let {
                if (it is Animatable) {
                    it.stop()
                }
            }
        }


        val tvSpan = findViewById<TextView>(R.id.tv_span)

        val drawable = createADrawable()
        val imgSpan = ImageSpan(drawable)

        val ss = SpannableString("ImageSpan *")
        ss.setSpan(imgSpan, 10, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvSpan.text = ss

        val drawableStart = createADrawable()
        tvSpan.setCompoundDrawables(drawableStart, null, null, null)

        tvSpan.setOnClickListener {
//            drawable.callback = it //这种方式无效，Drawable和TextView之间无关联
            drawable.callback = object : Drawable.Callback {
                override fun invalidateDrawable(who: Drawable) {
                    //这也就是一个监听
                    it.invalidate()
                }

                override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
                    //View去定时绘制的
                    it.scheduleDrawable(who, what, `when`)
                }

                override fun unscheduleDrawable(who: Drawable, what: Runnable) {
                    it.unscheduleDrawable(who, what)
                }

            }
            drawable.start()
//            drawableStart.start()
        }

        val tvSpan2 = findViewById<TextView>(R.id.tv_span2)
        val infoBuilder = SpannableStringBuilder().append("Leobert")

        val madels = arrayListOf<String>("Lv.10", "持续创造", "笔耕不追", "夜以继日")
        val drawables: List<AnimLetterDrawable2> = madels.map { madel ->
            appendMadel(infoBuilder, madel).let { drawable ->
                drawable.callback = object : Drawable.Callback {
                    override fun invalidateDrawable(who: Drawable) {
                        tvSpan2.invalidate()
                    }

                    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
                        tvSpan2.scheduleDrawable(who, what, `when`)
                    }

                    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
                        tvSpan2.unscheduleDrawable(who, what)
                    }
                }
                drawable
            }
        }

        tvSpan2.text = infoBuilder
        tvSpan2.setOnClickListener {
            drawables.forEach {
                it.start()
            }
        }
        //通过textView.setMovementMethod(LinkMovementMethod.getInstance())可以将TextView设置为可点击链接的样式，使得其中的链接可以被点击并执行相应的操作
        //通过setMovementMethod()方法将TextView的移动方法设置为LinkMovementMethod，这样文本中的链接就可以被点击了
        //除了LinkMovementMethod，Android还提供了其他一些内置的移动方法，如ScrollingMovementMethod（滚动移动方法）和ArrowKeyMovementMethod（箭头键移动方法），它们可以实现不同的文本交互和滚动效果。同时，你也可以自定义自己的MovementMethod来实现特定的文本交互行为
        tvSpan2.movementMethod = LinkMovementMethod.getInstance()

        //TextView textView = findViewById(R.id.textView);
        //textView.setText("Long text that needs scrolling");
        //textView.setMovementMethod(new ScrollingMovementMethod());
        /*在上述示例中，我们首先获取到一个TextView实例，然后设置文本内容为"Long text that needs scrolling"。接下来，通过setMovementMethod()方法将TextView的移动方法设置为ScrollingMovementMethod。
        这样，当文本内容超出TextView的显示范围时，用户就可以在TextView中滚动文本，以查看剩余的内容。
        请注意，在使用ScrollingMovementMethod之前，确保TextView的文本内容足够长，以便在TextView中产生滚动效果。如果文本内容不够长，就无法滚动。
        另外，还要确保TextView的android:maxLines属性设置为一个较大的值，以允许多行文本的显示和滚动。*/

        val tvSpan3 = findViewById<TextView>(R.id.tv_span3)
        tvSpan3.text = infoBuilder
        tvSpan3.setOnClickListener {
            drawables.forEach {
                it.start()
            }
        }
        tvSpan3.movementMethod = LinkMovementMethod.getInstance()
    }

    fun createADrawable(): AnimLetterDrawable2 {
        val drawable = AnimLetterDrawable2()
        drawable.textSize = 20f
        drawable.letters = "span"
        drawable.setBounds(0, 0, 100, 100)


        return drawable
    }

    fun appendMadel(builder: SpannableStringBuilder, madel: String): AnimLetterDrawable2 {
        val drawable = AnimLetterDrawable2()
        drawable.textSize = 20f
        drawable.letters = madel
        drawable.setBounds(0, 0, 100, 100)

        val imgSpan = ImageSpan(drawable)
        val ss = SpannableString(" *")
        ss.setSpan(imgSpan, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        ss.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, madel, Toast.LENGTH_SHORT).show()
            }
        }, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        builder.append(ss)

        return drawable
    }
}