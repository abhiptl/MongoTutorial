package com.abhishek.mongodb.homework.week5;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 6/22/2016.
 */
public class Homework52 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

	MongoCollection<Document> zipsCollection = mongoDatabase.getCollection("zips");

	List<Document> pipeline = new ArrayList<>();
	Document groupBy = new Document("$group", new Document("_id", new Document("state", "$state").append("city", "$city")).append("pop", new Document("$sum", "$pop")));

	List<String> stateMatchingList = new ArrayList<>();
	stateMatchingList.add("CA");
	stateMatchingList.add("NY");
	Document matchDocument = new Document("$match", new Document("_id.state",
			new Document("$in", stateMatchingList)).append("pop", new Document("$gt", 25000)));

	Document group2 =  new Document("$group", new Document("_id", null).append("pop", new Document("$avg", "$pop")));

	pipeline.add(groupBy);
	pipeline.add(matchDocument);

	pipeline.add(group2);
	MongoUtil.printAggregateIterableDocuments(zipsCollection.aggregate(pipeline));
  }
}
