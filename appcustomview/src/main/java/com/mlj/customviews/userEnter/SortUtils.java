package com.mlj.customviews.userEnter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtils {
    public static void sortUserByCreateTime(List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return (int) (o2.getCreateTime() - o1.getCreateTime());
            }
        });
    }
}
