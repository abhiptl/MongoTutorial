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
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/slice/
 */
public class MongoSliceApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find first two grades of all restaurants in zipcode 07005");

	Document matchDocument = new Document("$match", new Document("address.zipcode", "07005"));

	Document sliceDocument = new Document();
	List sliceExpression = new ArrayList();
	sliceExpression.add("$grades");
	sliceExpression.add(2);

	sliceDocument.append("$slice", sliceExpression);

	Document projectDocument = new Document("$project", new Document("restaurant_id", 1).append("twoGrades", sliceDocument));
	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(matchDocument);
	aggregateListDocuments.add(projectDocument);


	MongoUtil.printAggregateIterableDocuments(restaurantsCollection.aggregate(aggregateListDocuments));
  }
}
