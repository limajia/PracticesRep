package com.docker.lib;

import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case //end 结束，只要是输入状态就是hasNext
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(a + b);
        }
        System.out.println("sdfs");
        /*in.hasNext();
        in.hasNextInt();
        in.hasNextLine();*/
    }
}
