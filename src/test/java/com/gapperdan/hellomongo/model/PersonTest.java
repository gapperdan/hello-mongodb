package com.gapperdan.hellomongo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private Person someone;

    @Test
    public void shouldCreateAMalePerson() {
        someone = Person.create(Gender.MALE);
        assertEquals(Gender.MALE, someone.getGender());
    }

    @Test
    public void shouldCreateAFemalePerson() {
        someone = Person.create(Gender.FEMALE);
        assertEquals(Gender.FEMALE, someone.getGender());
    }

    @Test
    public void shouldCreateAPersonWithTheCorrectAttributes() {
        someone = Person.create(Gender.FEMALE);
        someone.setFirstName("First");
        someone.setLastName("Last");
        someone.setAge(99);

        assertEquals(someone.getFirstName(), "First");
        assertEquals(someone.getLastName(), "Last");
        assertEquals(someone.getAge(), 99);
        assertEquals(someone.getGender(), Gender.FEMALE);

    }
}
