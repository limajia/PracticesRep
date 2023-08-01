package com.mlj.practicesrep.multipointtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import com.mlj.practicesrep.R;

public class MultiPointTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_point_test);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked(); // 获取触摸事件动作代码
        int pointerIndex = event.getActionIndex(); // 获取当前活动手指的索引
        switch (action) {
            case MotionEvent.ACTION_DOWN://这两个down是不同的
            case MotionEvent.ACTION_POINTER_DOWN:
                // 新的手指按下
                //int pointerId = event.getPointerId(pointerIndex); // 获取特定手指的ID
                // 根据ID来区分不同的手指
                // 这里可以根据手指ID来记录手指的位置、状态等信息
                System.out.println("docker123 start--:action = " + action + " pointerIndex = " + event.getActionIndex() + " event.getPointerId = " + event.getPointerId(pointerIndex));
                break;

            case MotionEvent.ACTION_MOVE:
                // 手指移动
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int id = event.getPointerId(i); // 获取特定手指的ID
                    float x = event.getX(i); // 获取手指在屏幕上的X坐标
                    float y = event.getY(i); // 获取手指在屏幕上的Y坐标
                    // 根据ID来区分不同的手指，并处理相应的移动操作
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                // 手指抬起
                //pointerId = event.getPointerId(pointerIndex); // 获取特定手指的ID
                // 根据ID来区分不同的手指
                // 这里可以根据手指ID来处理手指抬起的操作
                System.out.println("docker123 end--:action = " + action + " pointerIndex = " + event.getActionIndex() + " pointerId= " + event.getPointerId(event.getActionIndex()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

        return true; // 返回true表示已处理触摸事件
    }
}
//http://i.lckiss.com/?p=305
//https://app.yinxiang.com/shard/s8/nl/16515318/72aba6a5-df29-4d28-86ed-c2a453372b6e/ 多点触摸总结

//总的来说，规律就是：
//
//        ACTION_MOVE时无法得知哪个具体的点被移动（getIndex只会返回一个值，若两个手指在移动呢？），所以获得的index是无效的，只能遍历数组index按id进行操作
//        常用语句块：
//
//        用于ACTION_DOWN时，先获取index，再捕获id进行具体跟踪操作：
//
//        int actionIndex = event.getActionIndex();
//        trackingPointerId = event.getPointerId(actionIndex);
//        在ACTION_MOVE时遍历所有pointer进行操作：
//
//        for (int i = 0; i < event.getPointerCount(); i++) {
//        pointerId = event.getPointerId(i);
//        //TODO
//        }
//        PS:多点触控的onTouchEvent方法中，应使用MotionEvent.getActionMasked() 替代MotionEvent.getAction() ，原因看源码：

/**
 * Return the masked action being performed, without pointer index information.
 * Use {@link #getActionIndex} to return the index associated with pointer actions.
 * @return The action, such as {@link #ACTION_DOWN} or {@link #ACTION_POINTER_DOWN}.
 **/
//public final int getActionMasked() {
//        return nativeGetAction(mNativePtr) & ACTION_MASK;
//        }
//        在多点的情况下，使用getActionMasked会更安全，getActionMasked获取的信息中不包含index信息，从而不会影响获取的事件准确性。
//
//        多点触控常见的三种类型
//        接力型
//
//        一个手指按下时，响应该手指，按下第二个手指时，第二个手指接替第一手指的工作继续响应。即：同一时刻，只有一个pointer起作用，也就是最新的pointer。
//
//        实现方式：追踪一个pointer id进行操作，每次在ACTION_POINTER_DOWN/UP时将追踪的id更新为最新的ID，在后续的ACTION_MOVE等操作中根据这个id来进行行为判断。
//
//        举例：ListView RecyleView
//
//        配合型/协作型
//
//        所有按下的手指共同产生作用，比如双指手势，多指手势。
//
//        实现方式：在ACTION_DOWN/POINTER_DOWN、ACTION_UP/POINTER_UP 时使用所有poiner的左边共同更新焦点坐标，在ACTION_MOCE等之后的事件中使用焦点坐标进行行为判断。
//
//        举例：双指左滑/右滑、三指唤出菜单、五指唤出菜单，均需要以各个点的位置计算公共焦点位置，再进行处理。
//
//        独立型
//
//        各个pointer做不同的事，互不影响。
//
//        实现方式：在每个ACTION_DOWN/POINTER_DOWN中记录每个pointer的id，根据id（不要根据index，上面有说index是会发生变化的），在后续的ACTION_MOVE等事件中针对每个id进行单独的跟踪。
//
//        举例：电子白板的10点书写，每个手指画出轨迹不一样的线条。
//
//        PS:以上来自扔物线，本人仅做复述以及部分补充。

