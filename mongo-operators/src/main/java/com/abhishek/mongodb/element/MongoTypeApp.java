package com.abhishek.mongodb.element;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/14/2016.
 * @link https://docs.mongodb.com/manual/reference/operator/query/type/#op._S_type
 */
public class MongoTypeApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find all Restaurants with zipcode field having String typeand zipcode=10462");

	Document typeDocument = new Document("$type", "string").append("$eq", "10462");
	Document zipcodeDocument = new Document("address.zipcode", typeDocument);

	System.out.println("Type Expression: "+zipcodeDocument.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(zipcodeDocument));
  }
}
