package com.docker.threadtestlib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 可重入锁 和不可重入锁 https://blog.csdn.net/ke1ying/article/details/117418432?utm_term=%E4%B8%8D%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81%E4%BE%8B%E5%AD%90&utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduweb~default-1-117418432&spm=3001.4430
public class TestReentrantLock {
    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition(); //实现通信

    public static void main(String[] args) {
        TestReentrantLock testReentrantLock = new TestReentrantLock();
        testReentrantLock.testA();
    }

    public void testA() {
        try {
            lock.lock();
            //业务处理...
            testB();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }

    private void testB() {
        try {
            lock.lock();
            //业务处理...
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
