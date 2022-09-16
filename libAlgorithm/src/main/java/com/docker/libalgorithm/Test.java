package com.docker.libalgorithm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) {
        doA();
    }

    static void doA() {
        String stackTraceString = getStackTraceString(new Throwable());
        System.out.println(stackTraceString);
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

}
