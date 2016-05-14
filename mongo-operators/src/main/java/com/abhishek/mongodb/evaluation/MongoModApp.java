package com.abhishek.mongodb.evaluation;

import java.util.ArrayList;
import javax.print.Doc;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/14/2016.
 * @link https://docs.mongodb.com/manual/reference/operator/query/mod/#op._S_mod
 */
public class MongoModApp {
  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find All restaurants in zipcode=10462 and any of grades module by 10 is 0 ");

	ArrayList<Integer> modExpression = new ArrayList<>();
	modExpression.add(10);
	modExpression.add(0);

	Document modDocument = new Document("$mod", modExpression);

	Document scoreDocument = new Document("grades.score", modDocument).append("address.zipcode", "10462");
	System.out.println("MOD Expression: "+scoreDocument.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(scoreDocument));

  }

}
