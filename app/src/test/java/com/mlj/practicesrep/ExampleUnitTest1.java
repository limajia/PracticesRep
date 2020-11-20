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
}

