package com.example.libtestslibkotlintest.coroutine
import kotlinx.coroutines.*

//没有main()入口，这个方法也不会执行
/*val job = GlobalScope.launch {
    println("job: ${this.coroutineContext[Job.Key]}")//自定义的operation，comonanyObject 命名
    println("job: ${this.coroutineContext[Job]}")
}*/

//父job 可以组合和管理协程的层次结构以及它们的生命周期，确保在父协程取消时，所有子协程都会被取消，从而更好地控制协程的行为。
/*fun main() = runBlocking {
    val parentJob = Job() // 创建一个父作业

    val childJob1 = launch(parentJob) {
        delay(1000)
        println("Child job 1 completed")
    }

    val childJob2 = launch(parentJob) {
        delay(2000)
        println("Child job 2 completed")
    }

    // 等待所有子协程完成
    joinAll(childJob1, childJob2)

    println("All child jobs completed")

    // 取消父作业，这将自动取消所有子协程
    parentJob.cancel()

    // 等待父作业完成
    parentJob.join()

    println("Parent job completed")
}*/


import kotlinx.coroutines.*

//协程的Job传递
fun main() = runBlocking {
    val parentJob = Job() // 创建一个父作业

    val deferred1: Deferred<Int> = async(parentJob) {
        delay(1000)
        return@async 42
    }

    val deferred2: Deferred<Int> = async(parentJob) {
        delay(2000)
        return@async 10
    }

    // 等待并组合两个协程的结果
    val result1 = deferred1.await()
    val result2 = deferred2.await()

    println("Result 1: $result1")
    println("Result 2: $result2")

    // 取消父作业，这将自动取消所有子协程
    parentJob.cancel()

    // 等待父作业完成
    parentJob.join()
}

