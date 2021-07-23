package com.docker.threadtestlib;

public class TestCore {
    public static void main(String[] args) {
        long l = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory ="+  l/1024/1024);
        long l1 = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory ="+  l1/1024/1024);
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors ="+  i);
    }
}
