package com.gapperdan.hellomongo.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    private Person person;

    @Test
    public void shouldCreateAPerson() {
        person = new Person();
        assertTrue(person instanceof Person);
    }

    @Test
    public void shouldCreateAPersonWithTheCorrectAttributes() {

        person = new Person();
        person.setFirstName("First");
        person.setLastName("Last");
        person.setAge(99);
        person.setGender(Gender.MALE);

        assertEquals(person.getFirstName(), "First");
        assertEquals(person.getLastName(), "Last");
        assertEquals(person.getAge(), 99);
        assertEquals(person.getGender().toString(),"MALE");

    }
}
