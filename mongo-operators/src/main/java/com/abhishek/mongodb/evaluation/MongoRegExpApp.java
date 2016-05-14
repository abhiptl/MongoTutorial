package com.abhishek.mongodb.evaluation;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/14/2016.
 * @link https://docs.mongodb.com/manual/reference/operator/query/regex/#op._S_regex
 */
public class MongoRegExpApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find All restaurants in zipcode=10462 and name start with Morris or morris ");

	Document regExpression = new Document("$regex","^Morris").append("$options", "i");
	Document nameDocument = new Document("name", regExpression).append("address.zipcode", "10462");

	System.out.println("Reg Exp Expression: "+nameDocument.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(nameDocument));

  }
}
