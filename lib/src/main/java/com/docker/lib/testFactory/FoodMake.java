package com.docker.lib.testFactory;

//以水果工厂 和 零食工厂 为例

// 从外向里读 FoodMakeFactory
public interface FoodMake<Source, Food> { //对象（包含具体过程）
    Food makeFood(Source source);

    // 默认是static 就是一个独立的类 和内部类不一样 需要外部类创建
    public static abstract class Factory<Food> {
        //需要抽出水果工厂和零食工厂的共同点 【如手动 自动】在类中只有一个class 因为是static
        // Factory<Food>这里最好不要Factory<Source，Food>有多个的source 和FoodMake 冲突，最好是写一个类确定一个类型，减少一个需要确定的类型
        public Factory() {
        }

        // 工厂返回一个对象（包含具体过程）
        abstract FoodMake<String, Food> genHandMake();

        abstract FoodMake<String, Food> genAutoMake(long startMakeTime);
    }
}

class AppleFactory extends FoodMake.Factory<Apple> { //和外面的泛型没有关系 因为是static

    @Override
    FoodMake<String, Apple> genHandMake() {
        return new AppHandMake();
    }

    @Override
    FoodMake<String, Apple> genAutoMake(long startMakeTime) {
        System.out.println("工厂的工作：" + startMakeTime + "时间后开始生产苹果");// 这里面向对象思想
        return new AppAutoMake();
    }
}

class AppHandMake implements FoodMake<String, Apple> {
    @Override
    public Apple makeFood(String s) {
        Apple apple = new Apple("用" + s + "制作红苹果");
        apple.isAutoMake = false;
        return apple;
    }
}

class AppAutoMake implements FoodMake<String, Apple> {
    @Override
    public Apple makeFood(String s) {
        Apple apple = new Apple("用" + s + "制作红苹果");
        apple.isAutoMake = true;
        return apple;
    }
}

class Apple {
    public boolean isAutoMake;

    public Apple(String name) {
        this.name = name;
    }

    public String name;

    @Override
    public String toString() {
        return "Apple{" +
                "isAutoMake=" + isAutoMake +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        AppleFactory appleFactory = new AppleFactory();
        FoodMake<String, Apple> foodMake1 = appleFactory.genHandMake();
        Apple apple = foodMake1.makeFood("苹果籽");
        System.out.println(apple.toString());
        FoodMake<String, Apple> foodMake2 = appleFactory.genAutoMake(10);
        Apple apple1 = foodMake2.makeFood("自动苹果汁");
        System.out.println(apple1.toString());
    }
}
