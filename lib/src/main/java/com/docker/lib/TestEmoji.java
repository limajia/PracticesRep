package com.docker.lib;


public class TestEmoji {

    public static void main(String[] args) {
        // 将当前 code 转换为 16 进制数
//		int hex = Integer.parseInt("1F600", 16);
//		// 将当前 16 进制数转换成字符数组
//		char[] chars = Character.toChars(0x1F923);
//		String valueOf = String.valueOf(chars);
//		System.out.println(valueOf + "-");

        // https://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php 汉字unicode编码
        String nameString = "你好"; // 在unicode的范围是	4E00-9FA5
        System.out.println(nameString.toCharArray().length);//2
        System.out.println(nameString.length());// 2 char[]数组的大小
        System.out.println(nameString.getBytes().length);// 6 //使用utf-16格式存储
		/*
		// ? 一个emoji到底占用几个字节
		String conteString = "\uD83D\uDC33";
		System.out.println(conteString);
		System.out.println(conteString.toCharArray().length);//2 这是两个代理组成的，一个uicode码就是一个char
		System.out.println(conteString.length());// number of unicode unit // 2
		System.out.println(conteString.getBytes().length); // 4 // java这里使用的存储 转化是utf-8格式存储的byte个数
		String nameString = "你";
		System.out.println(nameString.toCharArray().length);//1
		System.out.println(nameString.length());// 1
		System.out.println(nameString.getBytes().length);// 3
		String charString = "h";
		System.out.println(charString.length());// 1
		System.out.println(charString.getBytes().length);// 1
		*/

        // String.getBytes()，默认是utf-8编码的：
        // ASCII（American Standard Code for Information
        // Interchange，美国信息交换标准代码）是基于拉丁字母的一套电脑编码系统。它主要用于显示现代英语。但这个只能显示的代表拉丁文，这显然是远远不够的。
        /*
         * Unicode 显而易见，计算机的发展并不是只支持英文一种语言的，ASCII的局限在于只能显示26个基本[拉丁字母]、[阿拉伯数字]和[英式标点符号]，
         * 因此只能用于显示现代美国英语。
         * 这时如果能有一种包含了世界上所有的文字的字符集，每一个地区的文字都在这个字符集中有唯一的二进制表示，这样便不会出现乱码问题了。
         * 所以Unicode也应运而生了。
         */

        // realStringLength(conteString);

        // 理解 使用UTF-16（存在超过16位，无法表示的情况，使用前置代理和后置代理的方式进行表示。）,UTF-8（更高级，读位确定几个字节为一个unicode 单元）的内存存储及解码格式 存储unicode字符唯一码位
    }

    /**
     * 包含emoji表情的字符串实际长度
     *
     * @param str 原有str
     * @return str实际长度
     */
    private static int realStringLength(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {//句子1
            char c = str.charAt(i); //句子2 两个句子可以理解为 char的个数 和 length的值是相同的。
            if (Character.isHighSurrogate(c) || Character.isLowSurrogate(c)) {
                i++;
            }
            count++;
        }

        return count;

    }
}
