package com.mlj.practicesrep;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    @Test
    public void addition_isCorrect() {
        ArrayList<MyUser> list = new ArrayList<>();
        list.add(new MyUser("111", 11));
        list.add(new MyUser("222", 11));

        List<MyUser> myUsers = removeDuplicateOutputField(list);
        for (MyUser myUser : myUsers) {
            System.out.println(myUser.name);
        }
    }

    /**
     * 根据list中对象某些字段去重
     *
     * @param list 需要去重的list
     * @return 返回去重后的list
     */
    private List<MyUser> removeDuplicateOutputField(List<MyUser> list) {
        Set<MyUser> set = new TreeSet<>(new Comparator<MyUser>() {
            @Override
            public int compare(MyUser o1, MyUser o2) {
                int compareToResult = 1;
                //==0表示重复
                //根据需求添加StringUtils.equals(o1.getUserName(), o2.getUserName()) ；
                if (o1.id == o2.id) {
                    compareToResult = 0;
                }
                return compareToResult;
            }
        });
        set.addAll(list);
        return new ArrayList<>(set);
    }

    class MyUser {
        public String name;
        public int id;

        public MyUser(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }
}

