package com.abhishek.mongodb.tutorial.week2;

/**
 * Created by Abhishek on 6/7/2016.
 */

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;
import org.bson.Document;

public class DocumentRepresentationApp {
    public static void main(String[] args) {
        Document doc = new Document();
        doc.put("userName", "Jaimin");
        doc.put("birthDate", new Date(234832423));
        doc.put("programmer", true);
        doc.put("age", 8);
        doc.put("languages", Arrays.asList("Java", "C++"));
        doc.put("address", new BasicDBObject("street", "20 Main")
                .append("town", "Westfield")
                .append("zip", "56789"));

        System.out.println("JSON Document:"+doc.toJson());
    }
}
