package com.abhishek.mongodb.aggregate.arithmatic;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/31/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/multiply/
 */
public class MongoMultiplyApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("sales");

	System.out.println("From sales collection display data(column) with multiplication of quantity and price");

	List multiplyExpression = new ArrayList();
	multiplyExpression.add("$price");
	multiplyExpression.add("$quantity");

	Document multiplyDocument = new Document("$multiply", multiplyExpression);

	Document projectDocument = new Document("$project", new Document("date", 1).append("item", 1).append("total", multiplyDocument));
	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(projectDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
