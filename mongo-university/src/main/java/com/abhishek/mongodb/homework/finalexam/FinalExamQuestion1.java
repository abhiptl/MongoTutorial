package com.abhishek.mongodb.homework.finalexam;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 07-Jul-16.
 */
public class FinalExamQuestion1 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("enron");

	MongoCollection<Document> messagesCollection = mongoDatabase.getCollection("messages");

	Document findDocument = new Document();
	findDocument.append("headers.From", "andrew.fastow@enron.com");
	findDocument.append("headers.To", "jeff.skilling@enron.com");

	ArrayList<Document> totalDocuments = messagesCollection.find(findDocument).into(new ArrayList<Document>());

	System.out.println("Total Email FROM(andrew.fastow@enron.com) to TO(jeff.skilling@enron.com) : "+totalDocuments.size());
  }
}
