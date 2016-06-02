package com.abhishek.mongodb.homework.week2;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Abhishek on 6/2/2016.
 *
 * Make sure you have dump necessary video db data before running this program using "mongorestore dump" utility
 */
public class Homework26 {

  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("video");
	final MongoCollection<Document> movieDetailsCollection = database.getCollection("movieDetails");

	MongoCursor<Document> documentMongoCursor = movieDetailsCollection.find(eq("countries.1", "Sweden")).iterator();

	int count = 0;

	while(documentMongoCursor.hasNext()){
	  documentMongoCursor.next();
	  count++;
	}

	System.out.println("Answer is :"+ count);


  }
}
