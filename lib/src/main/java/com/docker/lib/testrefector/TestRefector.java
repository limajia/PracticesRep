package com.docker.lib.testrefector;

import java.lang.reflect.InvocationTargetException;

public class TestRefector {

    public static void main(String[] args) {
        Man man = new Man();
        try {
            Human human = Human.class.getDeclaredConstructor().newInstance();
            System.out.println(human.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //java.lang.NoSuchMethodException: com.docker.lib.testrefector.Human.<init>()
        //	at java.lang.Class.getConstructor0(Class.java:3082)
        //	at java.lang.Class.getDeclaredConstructor(Class.java:2178)
        //	at com.docker.lib.testrefector.TestRefector.main(TestRefector.java:10)
    }
}


class Man implements Human {
    String name = "docker";
}

interface Human {
    int height = 180;
}
