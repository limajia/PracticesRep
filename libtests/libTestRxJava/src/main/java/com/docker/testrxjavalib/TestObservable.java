package com.docker.testrxjavalib;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TestObservable {
    public static void main(String[] args) {
        Observable.interval(0,1, TimeUnit.SECONDS)
                .subscribe();
    }
}
