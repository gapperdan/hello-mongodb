package com.gapperdan.hellomongo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private Person someone;

    @Test
    public void shouldCreateAMalePerson() {
        someone = Person.create(null, null, Gender.MALE, 0);
        assertEquals(Gender.MALE, someone.getGender());
    }

    @Test
    public void shouldCreateAFemalePerson() {
        someone = Person.create(null, null, Gender.FEMALE, 0);
        assertEquals(Gender.FEMALE, someone.getGender());
    }

    @Test
    public void shouldCreateAPersonWithTheCorrectAttributes() {
        someone = Person.create("First", "Last", Gender.FEMALE, 99);
        assertEquals(someone.getFirstName(), "First");
        assertEquals(someone.getLastName(), "Last");
        assertEquals(someone.getAge(), 99);
        assertEquals(someone.getGender(), Gender.FEMALE);
    }
}
