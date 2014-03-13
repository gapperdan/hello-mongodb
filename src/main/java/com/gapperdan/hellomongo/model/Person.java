package com.gapperdan.hellomongo.model;

import lombok.Data;

public @Data class Person {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

}
