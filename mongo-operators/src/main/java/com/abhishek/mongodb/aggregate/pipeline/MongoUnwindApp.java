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
 * Created by Abhihshek on 5/15/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/unwind/
 */
public class MongoUnwindApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find restaurant with id=30075445 and outputs/unwinds its grades");

	Document matchDocument = new Document("$match", new Document("restaurant_id", "30075445"));
	Document unwindDocument = new Document("$unwind", new Document("path", "$grades").append("includeArrayIndex", "arrayIdx"));

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(unwindDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
