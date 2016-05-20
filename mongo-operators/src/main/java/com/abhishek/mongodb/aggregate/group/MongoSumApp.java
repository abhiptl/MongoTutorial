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
 */
public class MongoSumApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find sum of score of restaurants grade by zipcode");

	//Document matchDocument = new Document("$match", new Document("address.zipcode", "10174"));
	Document groupByDocument = new Document("$group", new Document("_id", "$address.zipcode").append("totalGrade", new Document("$sum", "$grades.score")));

	Document unwindDocument = new Document("$unwind", new Document("path", "$grades").append("includeArrayIndex", "arrayIdx"));

	List<Document> aggregateListDocuments = new ArrayList<>();
	//aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(unwindDocument);
	aggregateListDocuments.add(groupByDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }

}
