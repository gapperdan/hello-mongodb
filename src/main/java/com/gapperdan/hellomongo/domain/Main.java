package com.gapperdan.hellomongo.domain;

import com.gapperdan.hellomongo.model.Gender;
import com.gapperdan.hellomongo.model.Person;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

public class Main {
    public static void main(String[] args) {

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("test");

            DBCollection collection = db.getCollection("person");

            //empty the collection first if not empty
            if (collection.getCount() > 0) {
                System.out.println("Deleting " +collection.getCount() + " documents from the collection");
                collection.remove(new BasicDBObject());
            }

            //create a person
            Person person;

            person = new Person();
            person.setFirstName("John");
            person.setLastName("Doe");
            person.setGender(Gender.MALE);
            person.setAge(29);

            //insert a document
            BasicDBObject basicDBObject;

            basicDBObject = new BasicDBObject();

            basicDBObject.append("firstname",person.getFirstName()).
                    append("lastname", person.getLastName()).
                    append("gender", person.getGender().toString()).
                    append("age", person.getAge());

            collection.insert(basicDBObject);
            System.out.println("Inserted person: "+person.toString());

            //create another person
            person = new Person();
            person.setFirstName("Mary");
            person.setLastName("Doer");
            person.setGender(Gender.FEMALE);
            person.setAge(27);
            basicDBObject = new BasicDBObject();

            basicDBObject.append("firstname",person.getFirstName()).
                    append("lastname", person.getLastName()).
                    append("gender", person.getGender().toString()).
                    append("age", person.getAge());

            collection.insert(basicDBObject);
            System.out.println("Inserted person: "+person.toString());

            //find one
            System.out.println("displaying one record");
            DBObject dbObject = collection.findOne();
            System.out.println(dbObject);

            //find all
            DBCursor dbCursor = collection.find();

            System.out.println("displaying all records");
            try {
                while (dbCursor.hasNext()) {
                    System.out.println(dbCursor.next());
                }
            } finally {
                dbCursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
