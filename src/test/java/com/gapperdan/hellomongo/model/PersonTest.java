package com.gapperdan.hellomongo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private Person someone;

    @Test
    public void shouldCreateAMalePerson() {
        someone = Person.create("Some", "Name", Gender.MALE, 0);
        assertEquals(Gender.MALE, someone.getGender());
    }

    @Test
    public void shouldCreateAFemalePerson() {
        someone = Person.create("Some", "Name", Gender.FEMALE, 0);
        assertEquals(Gender.FEMALE, someone.getGender());
    }

    @Test
    public void shouldCreateAPersonWithTheCorrectAttributes() {
        someone = Person.create("Joe", "Someone", Gender.MALE, 99);
        assertEquals(someone.getFirstName(), "Joe");
        assertEquals(someone.getLastName(), "Someone");
        assertEquals(someone.getAge(), 99);
        assertEquals(someone.getGender(), Gender.MALE);

        String userIdStringFirstFourChars = someone.getFirstName().substring(0,2).toLowerCase() +
                someone.getLastName().substring(0,2).toLowerCase();
        assertEquals(userIdStringFirstFourChars, someone.getFirstName().substring(0,2).toLowerCase()
                + someone.getLastName().substring(0,2).toLowerCase());
    }

}
