package com.abhishek.mongodb.homework.week2;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Sorts.ascending;

/**
 * Created by Abhishek on 6/1/2016.
 *
 * Import grades data before running this program and take student_id from console which is answer
 */
public class Homework22 {

  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("students");
	final MongoCollection<Document> collection = database.getCollection("grades");

	FindIterable<Document> limit = collection.find(gte("score", 65)).sort(ascending("score")).limit(1);
	MongoUtil.printFindIterableDocuments(limit);
  }
}
