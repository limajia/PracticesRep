package com.docker.lib;

import java.util.HashMap;

public class TestStatic {
	public static void main(String[] args) {
		TestInner.getHashMap();
	}
}

class TestInner {
	static HashMap<String, String> hashMap = new HashMap<String, String>() {
		{
			put("aaa", "bbb");
			System.out.println("构造代码块");
		}
	};
	
	public static HashMap<String, String> getHashMap() {
		System.out.println("调用get方法");
		return hashMap;
	}

	static {
		System.out.println("静态代码块执行");
		hashMap.put("fsdfd", "sfsf");
	}
}

//输出结果为:

//构造代码块
//静态代码块执行
//调用get方法
