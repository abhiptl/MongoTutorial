package com.abhishek.mongodb.homework.week2;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.and;

/**
 * Created by Abhishek on 6/2/2016.
 *
 * Make sure you have dump necessary video db data before running this program using "mongorestore dump" utility
 */
public class Homework25 {

  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("video");
	final MongoCollection<Document> movieDetailsCollection = database.getCollection("movieDetails");

	FindIterable<Document> documents = movieDetailsCollection.find(and(eq("rated", "PG-13"),eq("year", 2013),eq("awards.wins", 0))).projection(
			Projections.include("title"));
	MongoUtil.printFindIterableDocuments(documents);
	System.out.println("Answer(title) is :"+ documents.first().get("title"));


  }
}
