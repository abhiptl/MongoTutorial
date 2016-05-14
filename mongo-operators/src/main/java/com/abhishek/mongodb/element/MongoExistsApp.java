package com.abhishek.mongodb.element;

import javax.print.Doc;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/14/2016.
 */
public class MongoExistsApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find all Restaurants with cuisine fieldExist and cuisine=Chinese");

	Document existDocument = new Document("$exists", true).append("$eq", "Chinese");
	Document cuisineDocument = new Document("cuisine", existDocument);

	System.out.println("Exists Expression: "+cuisineDocument.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(cuisineDocument));
  }
}
