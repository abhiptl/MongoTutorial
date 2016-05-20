package com.abhishek.mongodb.aggregate.group;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/20/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/first/
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/last/
 */
public class MongoSortApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find highest and lowest score grade in each zipcode area");

	//This matchDocument is just to verify result of sort for particular restaurant
	//Document matchDocument = new Document("$match", new Document("address.zipcode", "10301"));

	Document sortDocument = new Document("$sort", new Document("grades.score", 1));
	Document groupByDocument = new Document("$group", new Document("_id", "$address.zipcode").append("highScore", new Document("$last", "$grades.score"))
	.append("lowScore", new Document("$first", "$grades.score")));

	Document unwindDocument = new Document("$unwind", new Document("path", "$grades").append("includeArrayIndex", "arrayIdx"));

	List<Document> aggregateListDocuments = new ArrayList<>();
	//aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(unwindDocument);
	aggregateListDocuments.add(sortDocument);

	aggregateListDocuments.add(groupByDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
