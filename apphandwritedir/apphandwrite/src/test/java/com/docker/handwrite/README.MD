https://blog.csdn.net/qq_20466945/article/details/106664087 手写简易Handler
用通俗易懂的话来阐述handler在android中起到的作用

处理延时任务
线程数据通信
先用文字描述一下各个类的具体情况：

Looper有一个MessageQueue消息队列
MessageQueue有一组待处理的Message
Message中有一个用于处理消息的Handler
Handler中有Looper和MessageQueue

public class Handler {
    private Looper mLooper;
    private MessageQueue mMessageQueue;

    public Handler() {
        mLooper = Looper.getInstance();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread " + Thread.currentThread()
                    + " that has not called Looper.prepare()");
        }
        mMessageQueue = mLooper.mMessageQueue;
    }

    public void handleMessage(Message message){

    }

    public void sendMessage(Message message){
        //指定工作的handler
        message.target = this;
        //放入消息队列
        mMessageQueue.enqueueMessage(message);
    }

    public void dispatchMessage(Message message){
        handleMessage(message);
    }
}

public class Message {
    Object object;
    int what;
    //指定工作的handler
    Handler target;
}

public class MessageQueue {

    //阻塞队列
    private BlockingQueue<Message> mMessageQueue = new ArrayBlockingQueue<>(50);

    /**
     * 将消息放入队列
     * @param message
     */
    public void enqueueMessage(Message message) {
        try {
            mMessageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将消息从队列中取出
     * @return
     */
    public Message next() {
        try {
            return mMessageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class Looper {
    //线程存储器
    private static final ThreadLocal<Looper> mThreadLocal = new ThreadLocal<>();
    //一个Looper只有一个消息队列
    public MessageQueue mMessageQueue;

    private Looper() {
        mMessageQueue = new MessageQueue();
    }

    /**
     * 创建指定线程的Looper实例
     */
    public static void prepare() {
        if (mThreadLocal.get() != null) {
            //一个线程只能创建一个Looper
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        mThreadLocal.set(new Looper());
    }

    /**
     * 获取指定线程的Looper实例
     * @return
     */
    public static Looper getInstance() {
        return mThreadLocal.get();
    }

    /**
     * 轮询消息
     */
    public static void loop() {
        Looper mLooper = getInstance();
        MessageQueue queue = mLooper.mMessageQueue;
        while (true) {
            Message message = queue.next();
            if (message != null) {
                //发送给handler
                message.target.dispatchMessage(message);
            }
        }
    }
}

// 使用
 	Looper.prepare();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message message) {
                //处理message
            }
        };

        new Thread(){
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }.start();
      Looper.loop();

我们在正常写项目的时候，没有去操作Looper.prepare()和Looper.loop()，准备和轮询这两个方法，是因为ActivityThread已经为我们准备好了。

再说一下ThreadLocal，threadlocal是一个线程内部的存储类，可以在指定线程内存储数据，数据存储以后，只有指定线程可以得到存储数据，获取方法就是get()和set()方法。

再用白话去描述一下整个过程：

哪个线程执行Looper.prepare()，就在这个线程下保存一个Looper对象，如果你多次执行，就会出RunTime异常，这也就是我们说的一个线程只有一个Looper对象，一个Looper对象管理一个MessageQueue队列，哪个线程创建Handler，handler就去找哪个线程去要那个Looper对象，目的是把自己要发送的消息放到这个线程的消息队列中，Message通过handler对象发送了出去，同时让message自己标记了是谁发送的自己，Looper轮询到了这个消息，再让message中标记的handler对象把事件消费了。