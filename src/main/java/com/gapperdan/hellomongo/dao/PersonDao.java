package com.gapperdan.hellomongo.dao;

import com.gapperdan.hellomongo.model.Person;
import java.util.List;

public interface PersonDao {
    public List<Person> getAll();

}
