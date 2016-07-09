package com.abhishek.mongodb.homework.finalexam;

import java.util.ArrayList;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 08-Jul-16.
 */
public class FinalExamQuestion8 {

  public static void main(String[] args) {
	MongoClient c =  new MongoClient();
	MongoDatabase db = c.getDatabase("test");
	MongoCollection<Document> animals = db.getCollection("animals");
	animals.drop();

	Document animal = new Document("animal", "monkey");

	animals.insertOne(animal);

	animal.remove("animal");
	animal.append("animal", "cat");

	animals.insertOne(animal);

	animal.remove("animal");
	animal.append("animal", "lion");

	animals.insertOne(animal);

	ArrayList<Document> totalAnimal = animals.find().into(new ArrayList<Document>());

	System.out.println("Total animals inserted :"+totalAnimal.size());
  }
}
