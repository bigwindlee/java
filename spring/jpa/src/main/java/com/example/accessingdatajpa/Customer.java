package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity /* indicating that it is a JPA entity, which is mapped to a table named Customer. */
public class Customer {

    @Id /* JPA recognizes it as the object’s ID. */
    @GeneratedValue(strategy = GenerationType.AUTO) /* should be generated automatically. */
    private Long id;

    private String firstName;

    private String lastName;

    // The default constructor exists only for the sake of JPA. You do not use it directly,
    // so it is designated as protected.
    protected Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
