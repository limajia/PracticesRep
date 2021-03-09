package com.mlj.practicesrep;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//在Java里面可以通过反射获取泛型信息的场景有三个，分别是：
//
//（1）成员变量的泛型
//
//（2）方法参数的泛型
//
//（3）方法返回值的泛型
//
//注意，通过对象本身也是没法获取的。
//
//不能通过发射获取泛型类型信息的场景有二个，分别是： 意思是T声明表示 去通过方法 成员 去获取T代表的
//
//（1）类或接口声明的泛型信息
//
//（2）局部变量的泛型信息


//泛型 TYPE
//泛型参数 Parameter

// 书写一个子类 去子类.class 或者 子类对象 去获取泛型类型 就是说使用的时候，这个类的泛型已经确定了。
// 而如果直接ArrayList<String> abc， abc.getClass这样它是Arraylist的的泛型信息 ArrayList<T> 是未知的 不可以获取。
// 意思就是 类已经确定了类型，而不是对象指定了一个类型。
public class TestGenerics {

    @Test
    public void maineeee() throws NoSuchMethodException, NoSuchFieldException {
        //1.
        System.out.println("测试泛型反射 方法返回值");
        Method method = MyClass.class.getMethod("getStringList", null);
        System.out.println(method.getReturnType());
        Type retrunType = method.getGenericReturnType();
        System.out.println(retrunType);

        if (retrunType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) retrunType;
            Type[] typeArguments = type.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                Class typeArgClass = (Class) typeArgument;
                System.out.println("泛型类型：" + typeArgClass);
            }
        }

        //2.
        System.out.println("获取泛型字段的类型信息");
        Field field = MyClass.class.getField("stringList");
        Type genericsFieldType = field.getGenericType();
        if (genericsFieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericsFieldType;
            Type[] fieldArgTypes = parameterizedType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("泛型字段的类型：" + fieldArgClass);
            }
        }

        //3.
        System.out.println("获取方法参数的泛型类型信息");
        Method method2 = MyClass.class.getMethod("setList", List.class);
        Type[] genericParameterTypes = method2.getGenericParameterTypes();
        for (Type genericType : genericParameterTypes) {
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type type : types) {
                    Class realType = (Class) type;
                    System.out.println("方法参数的类型：" + realType);
                }
            }
        }

        //4.
        System.out.println("获取泛型字段的类型信息------");
        Field field3 = GenericClass.class.getField("tFiled");
        Type genericsFieldType3 = field3.getGenericType();
        if (genericsFieldType3 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericsFieldType3;
            Type[] fieldArgTypes = parameterizedType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("泛型字段的类型：" + fieldArgClass);
            }
        }
        // 这里验证了 不可以获取 就算是指定了上下限也是不可以的
    }
}

//例子1
class MyClass {

    public List<String> stringList = new ArrayList<>();

    public List<String> getStringList() {
        return stringList;
    }

    public void setList(List<Integer> list) {
    }
}

//例子2
class GenericClass<T extends String> {

    public ArrayList<String> testFiled = new ArrayList<>();

    public T tFiled;

    public T gettFiled() {
        return tFiled;
    }

    public void settFiled(T tFiled) {
        this.tFiled = tFiled;
    }
}
