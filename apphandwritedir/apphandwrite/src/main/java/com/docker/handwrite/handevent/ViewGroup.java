package com.docker.handwrite.handevent;

import java.util.ArrayList;
import java.util.List;


public class ViewGroup extends View{
    //存放子控件
    List<View> childList = new ArrayList<>();
    private View[] mChildren = new View[0];
    private TouchTarget mFirstTouchTarget; //存放事件分发的顺序

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    /**
     * 添加子控件
     * @param view
     */
    public void addView(View view) {
        if(view == null) {
            return;
        }
        childList.add(view);
        mChildren = childList.toArray(new View[childList.size()]);
    }

    //事件分发的入口
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(getName() + "->ViewGroup dispatchTouchEvent");
        boolean handled = false;
        TouchTarget newTouchTarget = null;
        //判断是否需要拦截
        boolean intercepted = onInterceptTouchEvent(ev);
        int actionMasked = ev.getActionMasked();
        if(actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {
            //down事件
            if(actionMasked == MotionEvent.ACTION_DOWN) {
                //循环遍历控件 倒叙遍历
                View[] children = mChildren;
                //耗时 在源码中会根据Z轴重新排序
                for (int i = children.length - 1; i >= 0; i--) { //这里是处理，兄弟节点
                    //获取当前子控件
                    View child = mChildren[i];
                    //child能否接受事件
                    if(!child.isContainer(ev.getX(), ev.getY())) {
                        //不在控件内
                        continue;
                    }
                    //child能够接受事件
                    if(dispatchTransformedTouchEvent(ev, child)) {//注意这里，会循环的递归调用进去，然后再依次出来
                        //这里只处理单点触摸，没有处理多点触摸。。。！！！！，所以这里的链表可以用于处理多点触摸！！！ 这只是一个ViewGroup对象的一个链表
                        //责任链模式 Message
                        handled = true;
                        newTouchTarget = addTouchTarget(child);
                        break;
                    }
                }
            }
        }
        //没有事件传递链表（无子控件消费事件->自己用）
        if(mFirstTouchTarget == null) {
            dispatchTransformedTouchEvent(ev, null);
        }else { //有链表，则按照链表传递事件
            TouchTarget target = mFirstTouchTarget;
            while (target != null) {
                TouchTarget next = target.next;
                if(target == newTouchTarget) {
                    handled = true;
                }else {
                    dispatchTransformedTouchEvent(ev, target.child);
                }
                target = next;
            }

        }
        return handled;
    }

    /**
     * Adds a touch target for specified child to the beginning of the list.
     * Assumes the target child is not already present.
     */
    private TouchTarget addTouchTarget(View child) {
        //采用头插法，将节点插入到链表的头部
        TouchTarget target = TouchTarget.obtain(child);
        target.next = mFirstTouchTarget;
        mFirstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        //当前缓存的View
        private View child;
        //回收池
        private static TouchTarget sRecycledBin;
        //size
        private static int sRecycledCount;
        private static final Object sRecycleLock = new Object[0];
        //next
        public TouchTarget next;

        /**
         * 移除->链表移除节点操作
         * @param child
         * @return
         */
        public static TouchTarget obtain(View child) {
            TouchTarget target;
            synchronized (sRecycleLock) {
                if(sRecycledBin == null) {
                    target = new TouchTarget();
                }else {
                    target = sRecycledBin;
                }
                sRecycledBin = target.next; //这是一个可以复用的链表
                sRecycledCount--;
                target.next = null;
            }
            target.child = child;
            return target;
        }

        /**
         * 回收->链表添加节点操作
         */
        public void recycle() {
            if(child == null) {
                throw new IllegalStateException("已经回收过了");
            }
            synchronized (sRecycleLock) {
                if(sRecycledCount < 32) {
                    //也是头插法 进行回收
                    next = sRecycledBin;
                    sRecycledBin = this;
                    sRecycledCount++;
                }
            }
        }

    }

    //分发事件到子控件中
    private boolean dispatchTransformedTouchEvent(MotionEvent ev, View child) {
        boolean handled = false;
        //当前
        if(child != null) {
            //传给子控件
            handled = child.dispatchTouchEvent(ev);
        }else {
            handled = super.dispatchTouchEvent(ev);
        }
        return handled;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
