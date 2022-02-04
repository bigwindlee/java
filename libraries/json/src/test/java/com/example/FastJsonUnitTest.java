package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import org.junit.jupiter.api.Test;

public class FastJsonUnitTest {

    /*
     * Convert Java Objects to JSON Format
     * gradle test --tests FastJsonUnitTest.whenJavaList_thanConvertToJsonCorrect
     */
    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
        List<Person> listOfPersons;
        listOfPersons = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JULY, 24);
        listOfPersons.add(new Person(15, "Doe", "John", new Date()));
        listOfPersons.add(new Person(20, "Doe", "Janette", new Date()));

        // 序列化：java object --> String
        String jsonOutput = JSON.toJSONString(listOfPersons);

        System.out.println("\nJava object List<Person> serialization:");
        System.out.println(jsonOutput);
    }

    /*
     * Create JSON Objects
     * gradle test --tests FastJsonUnitTest.whenGenerateJson_thanGenerationCorrect
     */
    @Test
    public void whenGenerateJson_thanGenerationCorrect() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL NAME", "Doe " + i);
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }
        String jsonOutput = jsonArray.toJSONString();
        System.out.println("\njsonArray serialization:");
        System.out.println(jsonOutput);
    }

    /*
     * Parse JSON Strings into Java Objects
     * gradle test --tests FastJsonUnitTest.whenJson_thanConvertToObjectCorrect
     */
    @Test
    public void whenJson_thanConvertToObjectCorrect() {
        Person person = new Person(20, "John", "Doe", new Date());

        /* 序列化 */
        String jsonObject = JSON.toJSONString(person);

        /* validate */
        JSONValidator jsonValidator = JSONValidator.from(jsonObject);
        assertTrue(jsonValidator.validate());

        /* 反序列化 */
        Person newPerson = JSON.parseObject(jsonObject, Person.class);

        /* 反序列化得到的对象和原对象进行比较 */
        assertEquals(newPerson.getAge(), 0); // because we set serialize to false
        assertEquals(newPerson.getFirstName(), person.getFirstName());
        assertEquals(newPerson.getLastName(), person.getLastName());

        /* Date包含time信息，并不适合作为生日，在比较时需要转化，去掉time信息。 */
        LocalDate oldDate = person.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newDate = newPerson.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(oldDate, newDate);
    }

    /*
     * Validate JSON String
     * gradle test --tests FastJsonUnitTest.whenJson_thanValidate
     */
    @Test
    public void whenJson_thanValidate() {
        JSONValidator okValidator = JSONValidator.from("{\"a\": 1, \"b\": 2}");
        assertTrue(okValidator.validate());

        /* 末尾多加一个逗号，validation failed. */
        JSONValidator errValidator = JSONValidator.from("{\"a\": 1, \"b\": 2,}");
        assertFalse(errValidator.validate());
    }

}
