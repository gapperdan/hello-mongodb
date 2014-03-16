package com.gapperdan.hellomongo.domain;

import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import com.gapperdan.hellomongo.service.PersonService;

public class Main {

    public static void main(String[] args) {

        try {
            PersonService personService = new PersonService();
            Person person;

            personService.deleteAll();

            person = Person.create("Bruce", "Wayne", Gender.MALE, 30);
            personService.add(person);
            person = Person.create("John", "Doe", Gender.MALE, 49);
            personService.add(person);
            person = Person.create("Jane", "Doer", Gender.FEMALE, 29);
            personService.add(person);

            System.out.println("added persons:");
            System.out.println(personService.getAll());

            System.out.println("searching for Bruce Wayne");
            person = personService.searchByName("Bruce", "Wayne");
            System.out.println("found: "+person.toString());

            System.out.println("updating Bruce Wayne's age to 199");
            personService.updateAge("Bruce", "Wayne", 199);
            System.out.println("after update:");
            person = personService.searchByName("Bruce", "Wayne");
            System.out.println("found: "+person.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
