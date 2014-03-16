package com.gapperdan.hellomongo.service;

import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    private Person someone;
    private List<Person> someList;
    private PersonService mockService;

    @Before
    public void setup() {
        mockService = new PersonService();
    }

    @Test
    public void shouldReturnAListOfPersons() {

        someList = new LinkedList<Person>();

        someone = Person.create("First", "Last", Gender.MALE, 99);
        someList.add(someone);

        someone = Person.create("First", "Last", Gender.FEMALE, 88);
        someList.add(someone);

        //mock the PersonService
        mockService = mock(PersonService.class);

        //when the mock personService is called, return the mock list
        try {
            when(mockService.getAll()).thenReturn(someList);

            //assert that there are persons in the list
            assertEquals(2, mockService.getAll().size());

            //assert that the 1st person is male
            assertTrue(Gender.MALE.equals(mockService.getAll().get(0).getGender()));

            //assert that the 2nd person is female
            assertTrue(Gender.FEMALE.equals(mockService.getAll().get(1).getGender()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnThePersonSearchedByName() {

        someone = new Person();
        someone.setFirstName("First");
        someone.setLastName("Last");
        someone.setGender(Gender.FEMALE);
        someone.setAge(99);

        mockService = mock(PersonService.class);
        try {
            when(mockService.searchByName("First", "Last")).thenReturn(someone);
            assertEquals("First", someone.getFirstName());
            assertEquals("Last", someone.getLastName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}