package com.gapperdan.hellomongo.dao;

import com.gapperdan.hellomongo.model.Person;
import java.util.List;

public interface PersonDao {
    public List<Person> getAll() throws Exception;
    public void add(Person person) throws Exception;
    public void clear() throws Exception;
}
