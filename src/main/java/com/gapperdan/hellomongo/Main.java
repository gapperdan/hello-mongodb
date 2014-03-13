package com.gapperdan.hellomongo;

import com.mongodb.*;

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

            //insert a document
            BasicDBObject basicDBObject;

            basicDBObject = new BasicDBObject();

            basicDBObject.append("name","joe").
                    append("gender", "male").
                    append("age", 39);

            collection.insert(basicDBObject);
            System.out.println("Document inserted");

            basicDBObject = new BasicDBObject();

            basicDBObject.append("name","jane").
                    append("gender", "female").
                    append("age", 29);

            collection.insert(basicDBObject);
            System.out.println("Document inserted");

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
