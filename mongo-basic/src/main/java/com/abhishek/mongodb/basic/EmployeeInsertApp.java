package com.abhishek.mongodb.basic;

import com.abhishek.mongodb.constant.MongoConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeInsertApp {

    public static void main(String arg[]){

        //1. Create Mongo Client
        MongoClient mongo = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

        //2. Get the by default database already created.
        MongoDatabase mongoDatabase = mongo.getDatabase("testdb");

        //3. Get collection. If not exist , it create new collection
        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        //4. Perform basic operation
        Document insertEmpDocument = new Document();
        insertEmpDocument.append("employeeId", "537664");
        insertEmpDocument.append("firstName", "Nikunj");
        insertEmpDocument.append("lastName", "Paramar");
        insertEmpDocument.append("doj", new Date());
        insertEmpDocument.append("designation", "Java Developer");
        List<String> languagesKnown = new ArrayList<String>();
        languagesKnown.add("Java");
        languagesKnown.add("Spring");
        insertEmpDocument.append("knownLanguages", languagesKnown);

        employeeCollection.insertOne(insertEmpDocument);

        //5. Retrieve all documents of collection(employee)
        System.out.println("Retrieving all employee documents...");

        MongoCursor<Document> iterator = employeeCollection.find().iterator();
        while(iterator.hasNext()){
            Document empDocument = iterator.next();
            System.out.println("Emp Document :"+ empDocument.toString());
        }

        //6. Close the Mongo Connection
        mongo.close();

    }
}
