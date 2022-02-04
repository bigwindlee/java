package com.example;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Person {
    @JSONField(name = "AGE", serialize = false)
    private int age;

    @JSONField(name = "LAST NAME", ordinal = 2)
    private String lastName;

    @JSONField(name = "FIRST NAME", ordinal = 1)
    private String firstName;

    @JSONField(name = "DATE OF BIRTH", format = "yyyy-MM-dd", ordinal = 3)
    private Date dateOfBirth;

    public Person(int age, String firstName, String lastName, Date dateOfBirth) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFirstName(String fullName) {
        this.firstName = firstName;
    }

    public void setLastName(String fullName) {
        this.lastName = lastName;
    }
}

