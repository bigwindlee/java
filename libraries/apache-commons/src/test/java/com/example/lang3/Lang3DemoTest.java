package com.example.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Lang3DemoTest {

    @Test
    public void givenStringUtilsClass_whenCalledisBlank_thenCorrect() {
        assertTrue(StringUtils.isBlank(" "));
        assertTrue(StringUtils.isBlank("\t"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledisEmpty_thenCorrect() {
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("\t"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledisAllLowerCase_thenCorrect() {
        assertTrue(StringUtils.isAllLowerCase("abd"));
        assertFalse(StringUtils.isAllLowerCase("Abd"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledisAllUpperCase_thenCorrect() {
        assertTrue(StringUtils.isAllUpperCase("ABC"));
        assertFalse(StringUtils.isAllUpperCase("aBC"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledisMixedCase_thenCorrect() {
        assertTrue(StringUtils.isMixedCase("abC"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledisAlpha_thenCorrect() {
        assertTrue(StringUtils.isAlpha("abc"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledisAlphanumeric_thenCorrect() {
        assertTrue(StringUtils.isAlphanumeric("abc123"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledcontains_thenCorrect() {
        assertTrue(StringUtils.contains("abc", "ab"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledcontainsAny_thenCorrect() {
        assertTrue(StringUtils.containsAny("abc", 'a', 'b', 'c'));
    }

    @Test
    public void givenStringUtilsClass_whenCalledcontainsIgnoreCase_thenCorrect() {
        assertTrue(StringUtils.containsIgnoreCase("abc", "ABC"));
    }

    @Test
    public void givenStringUtilsClass_whenCalledswapCase_thenCorrect() {
        assertEquals(StringUtils.swapCase("abc"), "ABC");
    }

    @Test
    public void givenStringUtilsClass_whenCalledreverse_thenCorrect() {
        assertEquals(StringUtils.reverse("abc"), "cba");
    }

    @Test
    public void givenStringUtilsClass_whenCalleddifference_thenCorrect() {
        /* str2拿掉与str1的交集，剩下的部分 */
        // 这里的中文会导致执行 gradle test 时出现乱码，解决方法：在 build.gradle 中添加下面一行：
        //     [compileJava, compileTestJava]*.options*.encoding = 'UTF-8'
        assertEquals(StringUtils.difference("abc", "abd"), "d");
    }
}
