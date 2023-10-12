package com.example.libtestslibkotlintest.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


//async实行并发
//开启多个任务，并发执行，所有任务执行完之后，返回结果，再汇总结果继续往下执行。
//针对这种场景，解决方案有很多，比如 java 的 FeatureTask， concurrent 包里面的 CountDownLatch、Semaphore, Rxjava 提供的 Zip 变换操作等。
fun main1() = runBlocking {
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
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        val a = async(Dispatchers.IO, CoroutineStart.LAZY) {
            delay(1000) // 模拟耗时操作1
        }
        val b = async(Dispatchers.IO, CoroutineStart.LAZY) {
            delay(2000) // 模拟耗时操作2
        }
        a.start()
        b.start()
        a.await()
        b.await()
        println("外层开始执行")
    }
}