package com.abhishek.mongodb.week1;

import java.net.UnknownHostException;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/28/2016.
 *
 * Run this program once you successfully restore dump using "mongorestore dump"
 */
public class Homework1 {

  public static void main(String[] args) throws UnknownHostException {

	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("m101");

	MongoCollection<Document> hw1Collection = mongoDatabase.getCollection("hw1");

	FindIterable<Document> documents = hw1Collection.find().limit(1);

	MongoUtil.printFindIterableDocuments(documents);

	System.out.println("You will get your answer and just submit the assignment");
  }
}
