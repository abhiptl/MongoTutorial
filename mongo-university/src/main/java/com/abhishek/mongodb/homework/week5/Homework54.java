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
 * Created by Abhishek on 6/23/2016.
 */
public class Homework54 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

	MongoCollection<Document> postsCollection = mongoDatabase.getCollection("zips");

	List<Document> pipeline = new ArrayList<>();

	List subStrList = new ArrayList();
	subStrList.add("$city");
	subStrList.add(0);
	subStrList.add(1);
	Document project = new Document("$project", new Document("city_char", new Document("$substr", subStrList)).append("pop", "$pop"));

	Document groupBy = new Document("$group", new Document("_id", "$city_char").append("total_pop", new Document("$sum", "$pop")));

	Document matchDocument = new Document();
	List<String> citiesStartWithNumeric = new ArrayList<>();
	citiesStartWithNumeric.add("0");
	citiesStartWithNumeric.add("1");
	citiesStartWithNumeric.add("2");
	citiesStartWithNumeric.add("3");
	citiesStartWithNumeric.add("4");
	citiesStartWithNumeric.add("5");
	citiesStartWithNumeric.add("6");
	citiesStartWithNumeric.add("7");
	citiesStartWithNumeric.add("8");
	citiesStartWithNumeric.add("9");
	matchDocument.append("$match",new Document("_id", new Document("$in", citiesStartWithNumeric)));

	Document groupByTwo = new Document("$group", new Document("_id", null).append("total_pop", new Document("$sum", "$total_pop")));

	pipeline.add(project);
	pipeline.add(groupBy);
	pipeline.add(matchDocument);
	pipeline.add(groupByTwo);


	MongoUtil.printAggregateIterableDocuments(postsCollection.aggregate(pipeline));
  }
}
