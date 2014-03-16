package com.gapperdan.hellomongo.service;

import com.gapperdan.hellomongo.dao.MongoPersonDaoImpl;
import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import com.mongodb.DBObject;

import java.util.List;

public class PersonService {

    MongoPersonDaoImpl personDao = new MongoPersonDaoImpl();

    public List<Person> getAll() throws Exception {
        return personDao.getAll();
    }

    public void add(Person person) throws Exception {
        personDao.add(person);
    }

    public void deleteAll() throws Exception {
        personDao.deleteAll();
    }

    public Person searchByName(String firstname, String lastname) throws Exception {
        return personDao.searchByName(firstname, lastname);
    }

}