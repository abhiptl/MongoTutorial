package com.abhishek.mongodb.homework.finalexam;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.operation.DistinctOperation;
import org.bson.Document;

/**
 * Created by Abhishek on 07-Jul-16.
 */
public class FinalExamQuestion2 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("enron");

	MongoCollection<Document> messagesCollection = mongoDatabase.getCollection("messages");
	List<Document> pipeline = new ArrayList<>();

	//unwind headers.To
	Document unwind = new Document();
	unwind.append("$unwind", "$headers.To");

	//tricky step group by on objectId and headers.From to get distinct headers.To per messages
	Document groupByDocument = new Document("$group", new Document("_id", new Document("objId", "$_id").append("fromEmail", "$headers.From")).append("distinctTo",
			new Document("$addToSet", "$headers.To")));

	//unwinds header.To
	Document unwind2 = new Document();
	unwind2.append("$unwind", "$distinctTo");

	//group by on headers.From and headers.To to sum one to one mail counts
	Document groupBy = new Document("$group", new Document("_id", new Document("fromEmail", "$_id.fromEmail").append("toEmail", "$distinctTo"))
			.append("emailCount", new Document("$sum", 1)));

	Document sortBy = new Document("$sort", new Document("emailCount",-1));
	Document limit = new Document("$limit", 1);


	pipeline.add(unwind);
	pipeline.add(groupByDocument);
	pipeline.add(unwind2);
	pipeline.add(groupBy);

	pipeline.add(sortBy);
	pipeline.add(limit);

	MongoUtil.printAggregateIterableDocuments(messagesCollection.aggregate(pipeline));
  }
}
