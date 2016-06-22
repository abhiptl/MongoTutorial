package com.abhishek.mongodb.homework.week5;

import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.operation.AggregateOperation;
import org.bson.Document;

/**
 * Created by Abhishek on 6/22/2016.
 */
public class Homework51 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("blog");

	MongoCollection<Document> postsCollection = mongoDatabase.getCollection("posts");

	List<Document> pipeline = new ArrayList<>();

	Document unwind = new Document();
	unwind.append("$unwind", "$comments");

	Document groupBy = new Document("$group", new Document("_id", "$comments.author").append("totalComments", new Document("$sum", 1)));

	Document sortBy = new Document("$sort", new Document("totalComments",-1));
	Document limit = new Document("$limit", 1);

	pipeline.add(unwind);
	pipeline.add(groupBy);
	pipeline.add(sortBy);
	pipeline.add(limit);

	MongoUtil.printAggregateIterableDocuments(postsCollection.aggregate(pipeline));

  }
}
