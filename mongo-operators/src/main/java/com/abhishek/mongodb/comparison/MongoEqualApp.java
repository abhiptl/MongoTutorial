package com.abhishek.mongodb.comparison;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/12/2016.
 */
public class MongoEqualApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	Document equalityDocument = null;
	//Find Restaurant with name Morris Park Bake Shop
	//Document equalityDocument = new Document("name", new Document("$eq", "Morris Park Bake Shop"));
	equalityDocument = new Document("name", "Morris Park Bake Shop");

	FindIterable<Document> documents = restaurantsCollection.find(equalityDocument);
	MongoUtil.printFindIterableDocuments(documents);

	//Find Restaurant having grades 'A' in any data
	equalityDocument = new Document("grades.grade", "C");
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(equalityDocument));

  }
}
