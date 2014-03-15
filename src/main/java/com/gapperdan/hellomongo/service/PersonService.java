package com.gapperdan.hellomongo.service;

import com.gapperdan.hellomongo.dao.MongoPersonDaoImpl;
import com.gapperdan.hellomongo.model.Person;

import java.util.List;

public class PersonService {

    MongoPersonDaoImpl personDao = new MongoPersonDaoImpl();

    public List<Person> getAll() {
        return personDao.getAll();
    }

}