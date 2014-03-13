package com.gapperdan.hellomongo;

import com.mongodb.*;


public class Main {
    public static void main(String[] args) {

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("test");

            DBCollection collection = db.getCollection("person");

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
