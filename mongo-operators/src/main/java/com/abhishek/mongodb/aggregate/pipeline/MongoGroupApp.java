package com.abhishek.mongodb.aggregate.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/15/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/group/
 */
public class MongoGroupApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find different cuisine restaurants by count in zipcode=10462");

	Document matchDocument = new Document("$match", new Document("address.zipcode", "10462"));
	Document groupByDocument = new Document("$group", new Document("_id", "$cuisine").append("count", new Document("$sum", 1)));

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(groupByDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }

}
