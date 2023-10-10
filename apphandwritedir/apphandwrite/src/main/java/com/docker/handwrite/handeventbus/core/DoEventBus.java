package com.docker.handwrite.handeventbus.core;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoEventBus {

    private static DoEventBus instance = new DoEventBus();
    private Handler handler;
    /**
     * 注册的对象--对象中注册的方法信息列表
     */
    private HashMap<Object, List<SubscribleMethod>> cacheMap;
    private final ExecutorService executorService;

    private DoEventBus() {
        this.cacheMap = new HashMap<>();
        this.handler = new Handler(Looper.getMainLooper());
        executorService = Executors.newCachedThreadPool();
    }

    public static DoEventBus getDefault() {
        return instance;
    }

    // 注册 根据注解把相关的方法放入缓存列表中
    // 注册的是一个object对象，不是class类
    public void register(Object subscriber) {
        List<SubscribleMethod> subscribleMethods = cacheMap.get(subscriber);
        // 判断是否已经注册 不要重复注册
        if (subscribleMethods == null) {
            // 根据注解获取注解列表
            subscribleMethods = getSubscribleMethods(subscriber);
            cacheMap.put(subscriber, subscribleMethods);
        }
    }

    private List<SubscribleMethod> getSubscribleMethods(Object subscriber) {
        List<SubscribleMethod> list = new ArrayList<>();
        Class<?> aClass = subscriber.getClass();
        // 当前类一直向父类去找字节码文件类
        while (aClass != null) {
            // 排除系统的包的类
            String name = aClass.getName();
            if (name.startsWith("java.") ||
                    name.startsWith("javax.") ||
                    name.startsWith("android.") ||
                    name.startsWith("androidx.")) {
//                return null; 这里不能反回空
                break; // 跳出系统类的判断循环
            }
            // 1:getMethods(),该方法是获取本类以及父类或者父接口中所有的[公共]方法(public修饰符修饰的)
            // 2:getDeclaredMethods(),该方法是获取[本类]中的所有方法，包括私有的(private、protected、默认以及public)的方法。
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                DoSubscribe annotation = declaredMethod.getAnnotation(DoSubscribe.class); // 内部 Class<T> cls 就默认给T赋值DoSubscribe了 cls = DoSubscribe.class
                /**
                 * java 中Class<?> 中的？代表什么意思
                 * 给你举个例子bai，
                 * Class<Integer> cla;与Class<?> cl;
                 * 前一个表示cla只能du指向Integer这种类型，而后一zhi个cl表示可以dao指向任意类型。
                 * cla = Integer.class 可以，但zhuancla = Double.class就不可以。
                 * 但是cl = Integer.class 可以，cl = Double.class也可以 、
                 * ?是通配符。
                 *
                 *class是类的意思
                 * 他的作用相当于包装盒，可以将很多零散的方法包装到一个盒子中，这样管理起来就比较有条理了
                 */
                if (annotation == null) {
                    continue;
                }
                // 检测监听的方法 是不是符合要求
                // TODO: 2021/1/5 注意这里
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();// 这里反射不会 基本类型返回包装类 int 就是int integer就是integer
                if (parameterTypes.length != 1) {
                    throw new RuntimeException("只能接收一个对象参数");
                }
                // 创建信息类对象 进行保存
                DoThreadMode doThreadMode = annotation.threadMode();
                // 方法，线程模式，参数
                SubscribleMethod subscribleMethod = new SubscribleMethod(declaredMethod, doThreadMode, parameterTypes[0]);
                list.add(subscribleMethod);
            }
            aClass = aClass.getSuperclass();
        }
        return list;
    }

    public void unRegister(Object subscriber) {
        List<SubscribleMethod> list = cacheMap.get(subscriber);
        if (list != null) {
            list.clear();
            cacheMap.remove(list);
        }
    }

    public void post(final Object event) {
        Set<Object> objects = cacheMap.keySet();
        Iterator<Object> iterator = objects.iterator();
        // 遍历所有的对象的 所有的方法
        while (iterator.hasNext()) {
            // 拿到注册类
            final Object next = iterator.next();
            // 遍历注册类中的所有方法
            List<SubscribleMethod> list = cacheMap.get(next);
            for (final SubscribleMethod subscribleMethod : list) {
                // 判断方法 是否需要接受事件
                if (subscribleMethod.getEventType().isAssignableFrom(event.getClass())) {
                    switch (subscribleMethod.getThreadMode()) {
                        case POSTING:
                            break;
                        case MAIN:
                            //如果接收方法在主线程执行的情况
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(subscribleMethod, next, event);
                            } else {
                                //post方法执行在子线程中，接收消息在主线程中
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribleMethod, next, event);
                                    }
                                });
                            }
                            break;
                        case MAIN_ORDERED:
                            break;
                        case BACKGROUND:
                            break;
                        case ASYNC:
                            // 在子线程中执行
                            //post方法执行在主线程中
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribleMethod, next, event);
                                    }
                                });
                            } else {
                                //post方法执行在子线程中
                                invoke(subscribleMethod, next, event);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + subscribleMethod.getThreadMode());
                    }
                }
            }
        }
    }

    private void invoke(SubscribleMethod subscribleMethod, Object obj, Object event) {
        Method method = subscribleMethod.getMethod();
        try {
            method.invoke(obj, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
