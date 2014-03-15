package com.gapperdan.hellomongo.model;

import lombok.Data;

public @Data class Person {

    //Factory Method instead of Constructor
    public static Person create(Gender gender) {
        Person person = new Person();
        person.setGender(gender);
        return person;
    }

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

}
