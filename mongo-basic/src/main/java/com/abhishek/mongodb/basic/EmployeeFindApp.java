package com.abhishek.mongodb.basic;


import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class EmployeeFindApp {

    public static void main(String arg[]){

        MongoClient mongo = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

        MongoDatabase mongoDatabase = mongo.getDatabase("testdb");

        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        System.out.println("Finding all employees");
        FindIterable<Document> allEmployee = employeeCollection.find();
        MongoUtil.printFindIterableDocuments(allEmployee);

        System.out.println("Finding all patel developers");
        Document queryDocument = new Document("lastName", "Patel");
        FindIterable<Document> allPatelDevelopers = employeeCollection.find(queryDocument);
        MongoUtil.printFindIterableDocuments(allPatelDevelopers);

        System.out.println("Finding all employees having designation=Java developer using Filters");
        FindIterable<Document> javaDevelopers = employeeCollection.find(Filters.eq("designation", "Java Developer"));
        MongoUtil.printFindIterableDocuments(javaDevelopers);

        System.out.println("Find all employees who knows Spring language. This is seach in Arary field of Document");
        //FindIterable<Document> empsKnowingJava = employeeCollection.find(Filters.elemMatch("knownLanguages", Filters.eq("knownLanguages", "Java")));
        FindIterable<Document> empsKnowingSpring = employeeCollection.find(Filters.eq("knownLanguages", "Spring"));
        MongoUtil.printFindIterableDocuments(empsKnowingSpring);


        System.out.println("Find employees who are Java Developer and know JavaScript");
        Document javaDeveloperDocument = new Document("designation", "Java Developer");
        Document javaScriptKnowLanguage = new Document("knownLanguages", "Java Script");
        FindIterable<Document> javaDeveloperWithJavaScripKnowEmps = employeeCollection.find(Filters.and(javaDeveloperDocument, javaScriptKnowLanguage));
        MongoUtil.printFindIterableDocuments(javaDeveloperWithJavaScripKnowEmps);


        System.out.println("Find all employees order by Data of Joining descending");
        FindIterable<Document> employeesOrderByDateOfJoining = employeeCollection.find().sort(Sorts.descending("doj"));
        MongoUtil.printFindIterableDocuments(employeesOrderByDateOfJoining);

        mongo.close();
    }

}
