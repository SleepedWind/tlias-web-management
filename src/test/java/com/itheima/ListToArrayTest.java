package com.itheima;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListToArrayTest {

    @Test
    void listToArray(){
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        String[] arr = list.toArray(new String[0]);
        for (int i = 0; i < 3; i++) {
            System.out.println(arr[i]);
        }

    }
}
