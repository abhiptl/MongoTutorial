package com.abhishek.mongodb.update.field;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/15/2016.
 */
public class MongoSetApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find All restaurants where any dated grades of Restaurant has grade=A and score>=20");

	Document findDocument = new Document("restaurant_id", "30075445");

	Document streetSetDocument = new Document("address.street", "Morris Park Avee");
	Document setDocument = new Document("$set", streetSetDocument);

	System.out.println("Before Updating ---");
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(findDocument));

	Document oneAndUpdate = restaurantsCollection.findOneAndUpdate(findDocument, setDocument);

	System.out.println("After Updating ---");
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(findDocument));

  }
}
