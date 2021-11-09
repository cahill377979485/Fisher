package com.cahill.fisher;

import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.LogUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() {
        String str = Checker.getThisWeekStart();
        assertEquals("2021-11-08", str);
    }
}