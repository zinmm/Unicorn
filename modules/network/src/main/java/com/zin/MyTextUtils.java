package com.zin;

/**
 * Create by ZhuJinMing on 2019/01/21
 */
public class MyTextUtils {

    public static int countStr(String str, char key) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == key)
                count++;
        }
        return count == 0 ? -1 : count;
    }
}
