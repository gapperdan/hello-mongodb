package com.gapperdan.hellomongo.dao;

import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import com.gapperdan.hellomongo.util.MongoConfig;
import com.gapperdan.hellomongo.util.Util;
import com.mongodb.*;
import org.aeonbits.owner.ConfigFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MongoPersonDaoImpl implements PersonDao {

    static final String FLD_FIRSTNAME = "firstname";
    static final String FLD_LASTNAME = "lastname";
    static final String FLD_AGE = "age";
    static final String FLD_GENDER = "gender";
    static final String FLD_USERID = "userid";

    MongoClient mongoClient;
    DBCursor dbCursor;
    DB db;
    DBCollection dbCollection;
    BasicDBObject basicDBObject;

    String mongoHost;
    int mongoPort;
    String mongoDb;
    String mongoCollection;

    public MongoPersonDaoImpl() {
        try {
            MongoConfig mongoConfig = ConfigFactory.create(MongoConfig.class);
            this.mongoHost = mongoConfig.mongoHost();
            this.mongoPort = mongoConfig.mongoPort();
            this.mongoDb = mongoConfig.mongoDb();
            this.mongoCollection = mongoConfig.mongoCollection();

            this.mongoClient = new MongoClient(mongoHost, mongoPort);
            this.db = this.mongoClient.getDB(mongoDb);
            this.dbCollection = this.db.getCollection(mongoCollection);
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
                    append(FLD_AGE, person.getAge()).
                    append(FLD_USERID, person.getUserId());

            dbCollection.insert(basicDBObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteAll() throws Exception {
        dbCollection = db.getCollection(mongoCollection);

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

    @Override
    public void updateAge(String firstname, String lastname, int age) throws Exception {

        BasicDBObject currentBasicDBObject = new BasicDBObject();
        BasicDBObject newBasicDBObject = new BasicDBObject();

        try {

            Person person = searchByName(firstname, lastname);

            currentBasicDBObject.append(FLD_FIRSTNAME, person.getFirstName().toUpperCase()).
                    append(FLD_LASTNAME, person.getLastName().toUpperCase()).
                    append(FLD_GENDER, person.getGender().toString()).
                    append(FLD_AGE, person.getAge()).
                    append(FLD_USERID, person.getUserId());

            newBasicDBObject.append(FLD_FIRSTNAME, person.getFirstName().toUpperCase()).
                    append(FLD_LASTNAME, person.getLastName().toUpperCase()).
                    append(FLD_GENDER, person.getGender().toString()).
                    append(FLD_AGE, age).
                    append(FLD_USERID, person.getUserId());

            DBObject dbObject = dbCollection.findAndModify(basicDBObject, newBasicDBObject);

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
        person.setUserId(dbObject.get(FLD_USERID).toString());

        return person;
    }
}