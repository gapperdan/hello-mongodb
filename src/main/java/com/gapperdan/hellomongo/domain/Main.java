package com.gapperdan.hellomongo.domain;

import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import com.gapperdan.hellomongo.service.PersonService;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

public class Main {
    public static void main(String[] args) {

        try {
            PersonService personService = new PersonService();
            Person person;

            personService.clear();

            person = new Person();
            person.setFirstName("Bruce");
            person.setLastName("Wayne");
            person.setGender(Gender.MALE);
            person.setAge(30);
            personService.add(person);

            person = new Person();
            person.setFirstName("John");
            person.setLastName("Doe");
            person.setGender(Gender.MALE);
            person.setAge(29);
            personService.add(person);

            person = new Person();
            person.setFirstName("Mary");
            person.setLastName("Doer");
            person.setGender(Gender.FEMALE);
            person.setAge(27);

            personService.add(person);

            System.out.println("added persons:");
            System.out.println(personService.getAll());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
