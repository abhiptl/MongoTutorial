package com.abhishek.mongodb.homework.week4;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

/**
 * Created by Abhishek on 6/16/2016.
 */
public class Homework44 {
  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("m101");
	final MongoCollection<Document> collection = database.getCollection("profile");

	FindIterable<Document> documents = collection.find(Filters.eq("ns", "school2.students")).sort(Sorts.descending("millis")).limit(1);

	MongoUtil.printFindIterableDocuments(documents);
	System.out.println("Decrease Latency :"+ documents.first().get("millis"));
  }
  }
