package com.zin.unicorn.unicorn;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String aa = "com.wole56.ishow_5.1.4_67";
        int index = aa.indexOf("_");
        aa = aa.substring(0, index);
        System.out.println(aa);
        assertEquals(4, 2 + 2);
    }
}