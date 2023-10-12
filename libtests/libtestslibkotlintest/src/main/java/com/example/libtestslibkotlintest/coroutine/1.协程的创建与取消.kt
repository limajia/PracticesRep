package com.example.libtestslibkotlintest

import kotlinx.coroutines.*

/*
创建协程的三种方式
1. 使用 runBlocking 顶层函数创建：
runBlocking {
    ...
}
1. 使用 GlobalScope 单例对象创建
GlobalScope.launch {
    ...
}
1. 自行通过 CoroutineContext 创建一个 CoroutineScope 对象
val coroutineScope = CoroutineScope(context)
coroutineScope.launch {
    ...
}
- 方法一通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的。
- 方法二和使用 runBlocking 的区别在于不会阻塞线程。但在 Android 开发中同样不推荐这种用法，因为它的生命周期会只受整个应用程序的生命周期限制，且不能取消。
- 方法三是比较推荐的使用方法，我们可以通过 context 参数去管理和控制协程的生命周期
*/



fun main() {
    /*1. 可以取消
    runBlocking {
         val job = launch {
             repeat(1000) { i ->
                 println("job: test $i ...")
                 delay(500L)
             }
         }
         delay(1300L) // 延迟一段时间
         println("main: ready to cancel!")
         job.cancel() // 取消该作业
         job.join() // 等待作业执行结束  //注意：这句话是wait外层线程，如果提前到Cancel之上的话，job.cancel()就不会执行了
         //job.cancelAndJoin()
         println("main: Now cancel.")
     }
 */

/*    //2.无法取消？？  协程并不是一定能取消，协程的取消是协作的。一段协程代码必须协作才能被取消。【理解的是这段执行逻辑必须是协程中的方法】
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 10) {
                // 一个执行计算的循环，只是为了占用 CPU
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: hello ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: ready to cancel!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now cancel.")
    }*/

/*    //3.修改2的问题 ,检查 job 状态isActive
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 10 && isActive) { //这里使用了协作
                // 一个执行计算的循环，只是为了占用 CPU
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: hello ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: ready to cancel!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now cancel.")
    }*/

    //4.修改2的问题 ,检查 job 状态，ensureActive()/yield() ，应该和java interrupt一样，这里的isActive是一个标志位，每次循环都会去检查，那些等待状态就会抛出interrupt异常
    /*runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 10) {
                ensureActive()// 这里使用了协作yield()
                // 一个执行计算的循环，只是为了占用 CPU
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: hello ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: ready to cancel!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now cancel.")
    }*/
    //5.修改2的问题 ,捕获异常
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(context = Dispatchers.Default) {
            try {
                var nextPrintTime = startTime
                var i = 0
                while (i < 10) {
                    ensureActive()// 这里使用了协作yield()
                    // 一个执行计算的循环，只是为了占用 CPU
                    // 每秒打印消息两次
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: hello ${i++} ...")
                        nextPrintTime += 500L
                        //throw CancellationException("test") 协程中抛出这个异常，认为是正常的不会崩溃，但是会结束协程的运行，其他的异常会崩溃
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                //在协程根布局中捕获异常，退出了协程。如果单纯的try catch 本提中的while循环还是会继续执行，把异常catch住了
                //scope中出现异常不会导致程序崩溃，协程内部自己处理了
            }
        }
        delay(1300L) // 等待一段时间
        println("main: ready to cancel!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now cancel.")
    }
    //6.任务超时withTimeout
    //我们之前没有在控制台上看到堆栈跟踪信息的打印。这是因为在被取消的协程中 CancellationException 被认为是协程执行结束的正常原因。
/*    runBlocking {
      val result = withTimeout(300) {
            println("start...")
            delay(100)
            println("progress 1...")
            delay(100)
            println("progress 2...")
            delay(100)//withTimeout 抛出了 TimeoutCancellationException，它是 CancellationException 的子类
            println("progress 3...")
            delay(100)
            println("progress 4...")
            delay(100)
            println("progress 5...")
            println("end")
        }
    }*/
    //还有另一种方式： 使用 withTimeoutOrNull。
    //withTimeout 是可以由返回值的，执行 withTimeout 函数，会阻塞并等待执行完返回结果或者超时抛出异常。
    // withTimeoutOrNull 用法与 withTimeout 一样，只是在超时后返回 null 。
}