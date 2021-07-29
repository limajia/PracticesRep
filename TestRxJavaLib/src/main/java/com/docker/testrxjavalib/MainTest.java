package com.docker.testrxjavalib;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainTest {
    public static void main(String[] args) {
/*        // Observable 相对于 single 太重 回调情况太多。Flowable比Observable 更加的重
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("发射数据");
                e.onComplete();
            }
        });

        //
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("MyClass.onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("我接收到数据了=" + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("MyClass.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("MyClass.onComplete");
            }
        };
        //
        stringObservable.subscribe(observer); //动作和属性  动作可以当做被处理对象的一个属性*/

        // single是有结果立即发送 无法Disposable取消
        Single.just("123")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return Integer.parseInt(s);
                    }
                })
                .map(new Function<Integer, String>() {

                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer + "";
                    }
                })// 逐渐包装
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("订阅");
                    }

                    @Override
                    public void onSuccess(String s) {
                        System.out.println("MyClass.onSuccess");
                        System.out.println("s = " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });//传入 触发操作
        // 一层 一层的包  最外层的一个函数触发，先调到最里层，执行完后在一层层调出来，类似于super的递归调用，
        // 关于调进去 逐层出来， 还是一路调进去，顺序是从里到外，还是从外到里。取决于类似 逻辑放在super.方法的前面或者后面

        //源码中 hook function.
        // io.reactivex.plugins.RxJavaPlugins.onAssembly(io.reactivex.Single<T>)
        // 其实就是一个过滤函数 添加一些自己的修改逻辑
    }
}