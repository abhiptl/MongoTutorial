package com.abhishek.mongodb.constant;

import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * Created by info on 4/24/2016.
 */
public class MongoUtil {

    public static void printAggregateIterableDocuments(AggregateIterable<Document> aggregateIterable){
        aggregateIterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        });
    }

    public static void printFindIterableDocuments(FindIterable<Document> findResultIterator){
        findResultIterator.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        });
    }
}
