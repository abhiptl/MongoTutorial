package com.abhishek.mongodb.homework.week3;

import java.util.ArrayList;

import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Sorts.ascending;

/**
 * Created by Abhishek on 6/8/2016.
 *
 * Import test data first before running this program if any
 */
public class Homework31 {

  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("school");
	final MongoCollection<Document> collection = database.getCollection("students");

	FindIterable<Document> limit = collection.find();

	System.out.println("Before performing update action");
	MongoUtil.printFindIterableDocuments(limit);

	MongoCursor<Document> iterator = limit.iterator();

	while(iterator.hasNext()){
	  Document studentScoreDocument = iterator.next();

	  int studentId = studentScoreDocument.getInteger("_id");
	  String name = studentScoreDocument.getString("name");

	  System.out.println("Student Name :"+name);

	  Document lowestScoreDocument = null;
	  Double minScore = 100.0;

	  ArrayList<Document> scores = (ArrayList) studentScoreDocument.get("scores");

	  for(Document score : scores){
		String type = score.getString("type");
		Double studentScore = score.getDouble("score");

		System.out.println("Type :"+type+" and score :"+studentScore);

		if (!"homework".equals(type)) {
		  continue;
		}

		if(studentScore < minScore){
		  lowestScoreDocument = score;
		  minScore = studentScore;
		}

	  }

	  System.out.println("Score to Remove :"+ lowestScoreDocument.get("score"));

	  if(lowestScoreDocument != null){
		scores.remove(lowestScoreDocument);

		Document scoreUpdate = new Document("$set", new Document("scores", scores));
		collection.updateOne(new Document("_id", studentId), scoreUpdate);

	  }
	}

	System.out.println("After performing update action");
	MongoUtil.printFindIterableDocuments(limit);
  }
}
