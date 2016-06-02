package com.abhishek.mongodb.homework.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

/**
 * Created by Abhishek on 6/2/2016.
 *
 * Load grades data if not loaded already. Then run this program to delete some documents and then run given commands in the homework to get answer.
 */
public class Homework23 {

  public static void main(String[] args) {
	MongoClient client = new MongoClient();

	MongoDatabase database = client.getDatabase("students");
	final MongoCollection<Document> collection = database.getCollection("grades");

	FindIterable<Document> homeworkTypeStudents = collection.find(Filters.eq("type", "homework")).sort(Sorts.ascending("student_id", "score"));

	//MongoUtil.printFindIterableDocuments(homeworkTypeStudents);

	System.out.println("No of Student grades before this operation should be 800 and actual is :"+ collection.count());

	int currentStudentId = -1;
	MongoCursor<Document> iterator = homeworkTypeStudents.iterator();
	while(iterator.hasNext()){
	  Document studentDocument = iterator.next();

	  int studentId = studentDocument.getInteger("student_id");

	  if(studentId != currentStudentId){
		collection.deleteOne(studentDocument);
		currentStudentId = studentId;
	  }

	}

	System.out.println("No of Student grades after this operation should be 600 and actual is :"+ collection.count());

  }
}
