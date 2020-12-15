package com.mlj.practicesrep;

import android.util.SparseArray;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest1 {
    @Test
    public void addition_isCorrect() {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(0, "1010");
        sparseArray.put(1, "8888");
        sparseArray.put(2, "9999");

        // sparseArray 为"null" size = 0 ？？？？？？？？？
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            System.out.println(sparseArray.keyAt(i));
        }
    }


    class AA {
        // --不写构造函数--的时候，会默认有一个无参数的构造函数
        // 如果写了构造函数，就没有了默认构造函数
        public AA(String ab) {

        }
    }

    class BB extends AA {
        //  父类有了有参数的构造函数后 必须 调用有参数的super 除非写了无参数的构造方法
        public BB(String ab) {
            super(ab);
        }
    }
}

