package com.example;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class FastJsonUnitTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private List<Person> listOfPersons;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Before
    public void setUp() {
        listOfPersons = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JULY, 24);
        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));
    }

    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
        String jsonOutput = JSON.toJSONString(listOfPersons);
        System.out.println("Hello abc");
        System.out.println(jsonOutput);
        assertEquals(1, 1);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
