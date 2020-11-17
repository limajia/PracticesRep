package com.mlj.practicesrep;

import androidx.annotation.CallSuper;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        /*String str = "aaabbb";
        String[] split = str.split("bhbh");
        System.out.println(split.length); // 长度为1 它本身*/

        Man man = new Man();
        man.doFood();
        man.needReWrite();
    }
}

class Man extends Human implements IDo {

    @Override
    public void needReWrite() {
        super.needReWrite();
        System.out.println("子类重写");
    }
}

class Human {
    //
    public void doFood() {
        System.out.println(this.toString());
    }

    @CallSuper // java 中不生效
    public void needReWrite() {
        System.out.println("父类需要重写的方法");
    }
}

interface IDo {
    void doFood();
}
// 接口实现 通过子类和父类的继承来完成

/*
@CallSuper注解主要是用来强调在覆盖父类方法的时候，需要实现父类的方法，及时调用对应的super.**方法，当使用 @CallSuper 修饰了某个方法，如果子类覆盖父类该方法后没有实现对父类方法的调用就会报错，如下所示：
class NULL {
    @CallSuper
    protected void Body(){
        System.out.println("Null_Body");
    }
}
class A extends NULL{
    protected void Body(){
        super.Body();
        System.out.println("A_Body");
    }
}
当使用@CallSuper 修饰某个方法后，子类覆盖该方法的时候就必须使用super.父类的该方法。
注意：java中是没有这个注解的。*/
