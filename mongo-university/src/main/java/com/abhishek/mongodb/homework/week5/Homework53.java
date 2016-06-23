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
public class Homework53 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

	MongoCollection<Document> zipsCollection = mongoDatabase.getCollection("grades");

	List<Document> pipeline = new ArrayList<>();

	//1 Unwind scores
	Document unwind = new Document();
	unwind.append("$unwind", "$scores");

	//2 Match score type in exam or homework
	Document matchDocument = new Document();
	List<String> gradeTypes = new ArrayList<>();
	gradeTypes.add("exam");
	gradeTypes.add("homework");
	matchDocument.append("$match",new Document("scores.type", new Document("$in", gradeTypes)));

	//3 Find average per class per student
	Document groupByOne = new Document("$group", new Document("_id", new Document("student_id", "$student_id").append("class_id", "$class_id")).
			append("classStudentAvg", new Document("$avg", "$scores.score")));

	//4 Find average per class
	Document groupByTwo =  new Document("$group", new Document("_id", new Document("class_id", "$_id.class_id")).append("classAvg", new Document("$avg", "$classStudentAvg")));

	//5 Sort average
	Document sort = new Document("$sort", new Document("classAvg", -1));

	pipeline.add(unwind);
	pipeline.add(matchDocument);
	pipeline.add(groupByOne);
	pipeline.add(groupByTwo);
	pipeline.add(sort);
	System.out.println("Take the first class id having best average student performance");
	MongoUtil.printAggregateIterableDocuments(zipsCollection.aggregate(pipeline));
  }
}
