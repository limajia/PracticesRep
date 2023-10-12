package com.docker.lib.testException;

import java.io.FileNotFoundException;

public class TestException {
    public static void main(String[] args) {
        MyLoader myLoader = new MyLoader();
        try {
            myLoader.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

interface ILoader {
    void load() throws UnsatisfiedLinkError;
}

class MyLoader
        //implements ILoader
{
   // @Override
    public void load() throws FileNotFoundException {
        System.out.println("sdfsdfsfsf");
    }
}

