package com.abhishek.mongodb.aggregate.array;

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
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/filter/
 */
public class MongoFilterAggreApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find all restaurants having grade greater than equal to 30 and display those grades with restaurant_id");

	Document filterDocument = new Document();
	List greaterThanExpression = new ArrayList();
	greaterThanExpression.add("$$grd.score");
	greaterThanExpression.add(30);

	filterDocument.append("$filter", new Document("input", "$grades").append("as", "grd").append("cond", new Document("$gte", greaterThanExpression)));

	Document projectDocument = new Document("$project", new Document("restaurant_id", 1).append("grades", filterDocument));

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(projectDocument);

	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
