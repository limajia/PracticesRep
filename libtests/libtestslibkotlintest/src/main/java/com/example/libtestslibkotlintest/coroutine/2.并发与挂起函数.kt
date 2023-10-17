package com.example.libtestslibkotlintest.coroutine

import android.os.SystemClock
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


//1.async实行并发
//开启多个任务，并发执行，所有任务执行完之后，返回结果，再汇总结果继续往下执行。
//针对这种场景，解决方案有很多，比如 java 的 FeatureTask， concurrent 包里面的 CountDownLatch、Semaphore, Rxjava 提供的 Zip 变换操作等。
/*fun main1() = runBlocking {
    val time = measureTimeMillis {//measureTimeMillis是一个统计方法耗时的封装
        val a = async(Dispatchers.IO) {
            delay(1000) // 模拟耗时操作1
        }
        val b = async(Dispatchers.IO) {
            delay(2000) // 模拟耗时操作2
        }
        a.await()
        b.await()
        println("外层开始执行")
    }
}*/


//2.惰性启动 async
/*fun main() = runBlocking {
    //在这个模式下，调用 await 获取协程执行结果的时候，或者调用Job的start方法时，协程才会启动。
    val time = measureTimeMillis {
        val a = async(Dispatchers.IO, CoroutineStart.LAZY) {
            delay(1000) // 模拟耗时操作1
            println("内层1执行")
        }
        val b = async(Dispatchers.IO, CoroutineStart.LAZY) {
            delay(2000) // 模拟耗时操作2
            println("内层2执行")
        }
        //a.start()
        //b.start()
        //a.await()
        //b.await() // 如果惰性启动没有启动，外层的runBlock，等待所有子协程执行完毕，不会结束，一直运行着！！！！！！！ 注意！！！
        println("外层开始执行")
        delay(6000)
        println("end……")
    }
    }*/

//3.挂起函数 delay就是一个挂起函数
/*public suspend fun delay(timeMillis: Long) {//源码
    if (timeMillis <= 0) return // don't delayreturn suspendCancellableCoroutine sc@ { cont: CancellableContinuation<Unit> ->
    // if timeMillis == Long.MAX_VALUE then just wait forever like awaitCancellation, don't schedule.if (timeMillis < Long.MAX_VALUE) {
    cont.context.delay.scheduleResumeAfterDelay(timeMillis, cont)
}*/
//方法签名用 suspend 修饰, 使用 suspend 关键字修饰的函数成为挂起函数，挂起函数只能在另一个挂起函数，或者协程中被调用。

