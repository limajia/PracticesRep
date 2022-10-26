package com.docker.threadtestlib;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String threadGroupName = Thread.currentThread().getThreadGroup().getName();
                int threadGroupPri = Thread.currentThread().getThreadGroup().getMaxPriority();
                System.out.println("threadName=" + threadName + "--threadGroupName=" + threadGroupName + "--threadGroupPri=" + threadGroupPri);
                // 这里是在子线程中执行的
            }
        };
        timer.schedule(timerTask, 5000, 1000);
    }
}
