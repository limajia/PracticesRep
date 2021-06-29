package com.docker.lib.testinstanceof;


public class TestMain {
    public static void main(String[] args) {
        Human human = new Man();
        System.out.println(human instanceof Man); //true
        System.out.println(human instanceof Human); //true

        Human human1  = new Human();
        Man man = ((Man) human1); // 崩溃
        System.out.println(man instanceof Man);
    }
}
