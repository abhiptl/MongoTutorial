package com.abhishek.mongodb.aggregate.date;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by info on 5/31/2016.
 */
public class MongoDateAggreApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Display year and month of all grades of restaurant with zipcode 07005");

	Document matchDocument = new Document("$match", new Document("address.zipcode", "07005"));

	Document unwindDocument = new Document("$unwind", new Document("path", "$grades").append("includeArrayIndex", "arrayIdx"));

	Document yearDocument = new Document("$year", "$grades.date");
	Document monthDocument = new Document("$month", "$grades.date");

	Document projectDocument = new Document("$project", new Document("restaurant_id", 1).append("month", monthDocument).append("year", yearDocument));
	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(unwindDocument);
	aggregateListDocuments.add(projectDocument);


	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
