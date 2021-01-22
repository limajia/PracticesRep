package com.mlj.practicesrep;

import org.junit.Test;

public class NullQiangzhuanTest {

    @Test
    public void testMain() {
        Person person = new Person();
        person = null;
        Human human = (Human) person; // null 强转是么有问题的
        if (human == null) {
            System.out.println("is null");
        } else {
            System.out.println(human.hashCode());
        }
    }


    class Human {

    }

    class Person extends Human {

    }
}
