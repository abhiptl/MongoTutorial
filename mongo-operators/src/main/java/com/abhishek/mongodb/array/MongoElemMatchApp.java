package com.abhishek.mongodb.array;

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
 */
public class MongoElemMatchApp {
  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find All restaurants where any dated grades of Restaurant has grade=A and score>=20");

	Document gradeDocument = new Document("grade", "A").append("score", new Document("$gte",20));

	List<Document> listAllDocuments = new ArrayList<>();

	listAllDocuments.add(new Document("$elemMatch", gradeDocument));

	Document allDocument = new Document("$all", listAllDocuments);
	Document gradesDocument = new Document("grades", allDocument);

	System.out.println("Array All,ElemMatch Expression : "+gradesDocument.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(gradesDocument));


  }
}
