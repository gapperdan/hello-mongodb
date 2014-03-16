package com.gapperdan.hellomongo.dao;

import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import com.gapperdan.hellomongo.util.Util;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MongoPersonDaoImpl implements PersonDao {

    static final String MONGO_HOST = "localhost";
    static final int MONGO_PORT = 27017;
    static final String MONGO_DB = "test";
    static final String MONGO_COLLECTION_PERSON = "person";

    static final String FLD_FIRSTNAME = "firstname";
    static final String FLD_LASTNAME = "lastname";
    static final String FLD_AGE = "age";
    static final String FLD_GENDER = "gender";

    MongoClient mongoClient;
    DBCursor dbCursor;
    DB db;
    DBCollection dbCollection;
    BasicDBObject basicDBObject;

    public MongoPersonDaoImpl() {
        try {
            this.mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
            this.db = this.mongoClient.getDB(MONGO_DB);
            this.dbCollection = this.db.getCollection(MONGO_COLLECTION_PERSON);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getAll() throws Exception {

        List<Person> persons = new ArrayList<Person>();

        try {
            dbCursor = dbCollection.find();

            try {
                while (dbCursor.hasNext()) {
                    DBObject dbObject = dbCursor.next();
                    persons.add(convertDbObjectToPerson(dbObject));
                }
            } finally {
                dbCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return persons;
    }

    @Override
    public void add(Person person) throws Exception {
        basicDBObject = new BasicDBObject();

        try {
            basicDBObject.append(FLD_FIRSTNAME,person.getFirstName().toUpperCase()).
                    append(FLD_LASTNAME, person.getLastName().toUpperCase()).
                    append(FLD_GENDER, person.getGender().toString()).
                    append(FLD_AGE, person.getAge());

            dbCollection.insert(basicDBObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteAll() throws Exception {
        dbCollection = db.getCollection(MONGO_COLLECTION_PERSON);

        try {
            if (dbCollection.getCount() > 0) {
                System.out.println("Deleting " +dbCollection.getCount() + " documents from the person collection");
                dbCollection.remove(new BasicDBObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Person searchByName(String firstname, String lastname) throws Exception {

        basicDBObject = new BasicDBObject();

        try {
            basicDBObject.append(FLD_FIRSTNAME,firstname.toUpperCase()).
                    append(FLD_LASTNAME, lastname.toUpperCase());

            DBObject dbObject = dbCollection.findOne(basicDBObject);
            return convertDbObjectToPerson(dbObject);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private Person convertDbObjectToPerson(DBObject dbObject) {
        Person person = new Person();

        person.setFirstName(Util.upperFirstCharOnly(dbObject.get(FLD_FIRSTNAME).toString()));
        person.setLastName(Util.upperFirstCharOnly(dbObject.get(FLD_LASTNAME).toString()));
        person.setGender(Gender.valueOf(dbObject.get(FLD_GENDER).toString()));
        person.setAge(Integer.valueOf(dbObject.get(FLD_AGE).toString()));

        return person;
    }

}