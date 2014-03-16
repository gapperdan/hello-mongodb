package com.gapperdan.hellomongo.model;

import lombok.Data;

public @Data class Person {

    //Factory Method instead of Constructor
    public static Person create(String firstName, String lastName, Gender gender, int age) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setAge(age);
        return person;
    }

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

}
