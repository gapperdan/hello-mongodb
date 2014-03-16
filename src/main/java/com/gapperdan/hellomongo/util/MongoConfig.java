package com.gapperdan.hellomongo.util;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:Mongo.properties"})
public interface MongoConfig extends Config {
    String mongoHost();
    int mongoPort();
    String mongoDb();
    String mongoCollection();
}
