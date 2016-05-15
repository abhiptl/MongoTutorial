package com.abhishek.mongodb.aggregate.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/15/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/project/
 */
public class MongoProjectApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find first 5 restaurants address and name in zipcode=11220");

	Document matchDocument = new Document("$match", new Document("address.zipcode", "11220"));
	Document projectDocument = new Document("$project", new Document("address", 1).append("name", 1));
	Document limitDocument = new Document("$limit", 5);

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(projectDocument);
	aggregateListDocuments.add(limitDocument);


	System.out.println("Project Expression: "+aggregateListDocuments);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
