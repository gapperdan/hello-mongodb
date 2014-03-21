package com.gapperdan.hellomongo.model;

import com.gapperdan.hellomongo.util.Util;
import lombok.Data;

public @Data class Person {

    //Factory Method instead of Constructor
    public static Person create(String firstName, String lastName, Gender gender, int age) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setAge(age);

        //generate the user id
        String firstPart = firstName.substring(0,2).toLowerCase();
        String secondPart = lastName.substring(0,2).toLowerCase();

        person.setUserId(firstPart
                + secondPart
                + Util.generateRandomNumberString(10)
                + Util.generateRandomNumberString(10)
                + Util.generateRandomNumberString(10)
                + Util.generateRandomNumberString(10));

        return person;
    }

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String userId;
}
