package com.abhishek.mongodb.homework.week2;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

/**
 * Created by Abhishek on 6/2/2016.
 */
public class ChallengeProblem2 {

  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("video");
	final MongoCollection<Document> movieDetailsCollection = database.getCollection("movieDetails");

	//Putting Filters.exists("tomato.consensus") is important to check if that field exist or not
	FindIterable<Document> documents = movieDetailsCollection.find(and(Filters.lt("imdb.votes", 10000) ,Filters.exists("tomato.consensus"), Filters.eq("tomato.consensus", null), Filters.in("year", asList(2010,2011,2012,2013))));
	MongoUtil.printFindIterableDocuments(documents);

  }

}
