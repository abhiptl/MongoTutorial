package com.abhishek.mongodb.aggregate.string;

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
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/substr/
 */
public class MongoSubStrApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("inventories");

	System.out.println("Display inventories with SubStr applied to Description");


	Document subStrDocument = new Document();
	List subStrExpression = new ArrayList();
	subStrExpression.add("$description");
	subStrExpression.add(1);
	subStrExpression.add(2);

	subStrDocument.append("$substr", subStrExpression);

	Document projectDocument = new Document("$project", new Document("sku", 1).append("description",1).append("descSubStr", subStrDocument));
	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(projectDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
