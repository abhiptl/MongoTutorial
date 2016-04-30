package com.abhishek.mongodb.basic;

import com.abhishek.mongodb.common.MongoConstant;
import com.abhishek.mongodb.common.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by info on 4/17/2016.
 */
public class EmployeeUpdateApp {

    public static  void main(String argp[]){
        MongoClient mongo = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

        MongoDatabase mongoDatabase = mongo.getDatabase("testdb");

        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        // Insert this document and perform different update operations.
        /*Document insertEmpDocument = new Document();
        insertEmpDocument.append("employeeId", "537665");
        insertEmpDocument.append("firstName", "Banshi");
        insertEmpDocument.append("lastName", "Sheth");
        insertEmpDocument.append("doj", new Date());
        insertEmpDocument.append("designation", "Fresher");
        List<String> languagesKnown = new ArrayList<String>();
        languagesKnown.add("Java");
        languagesKnown.add("Hibernate");
        insertEmpDocument.append("knownLanguages", languagesKnown);

        employeeCollection.insertOne(insertEmpDocument);
*/
        MongoUtil.printEmployeeDocuments(employeeCollection.find());

       /* System.out.println("Update all employee from Fresher to Java Developer");
        Document queryDocument = new Document("designation", "Fresher");
        Document updatedDocument = new Document("$set",new Document("designation", "Java Developer"));
        employeeCollection.updateMany(queryDocument, updatedDocument);
        printEmployeeDocuments(employeeCollection.find());*/


        /*System.out.println("Update employee first and last name by _Id");

        Document queryDocumentBYId = new Document("_id", new ObjectId("57138c898ce42c0d3cbc4563"));
        Document updatedDocument = new Document("$set",new Document("firstName", "Amrita").append("employeeId","537666").append("lastName","Chandani"));
        UpdateResult updateResult = employeeCollection.updateMany(queryDocumentBYId, updatedDocument);
        System.out.println("Update Result:"+updateResult.getModifiedCount());
*/

        MongoUtil.printEmployeeDocuments(employeeCollection.find());

   }

}
