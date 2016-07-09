package com.abhishek.mongodb.homework.finalexam;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * Created by Abhishek on 08-Jul-16.
 */
public class FinalExamQuestion3 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("enron");

	MongoCollection<Document> messagesCollection = mongoDatabase.getCollection("messages");

	System.out.println("Before updating document");
	MongoUtil.printFindIterableDocuments(messagesCollection.find(Filters.eq("headers.Message-ID", "<8147308.1075851042335.JavaMail.evans@thyme>")));

	Document pushDocument = new Document("$push", new Document("headers.To", "mrpotatohead@mongodb.com"));

	Document oneAndUpdate = messagesCollection
			.findOneAndUpdate(Filters.eq("headers.Message-ID", "<8147308.1075851042335.JavaMail.evans@thyme>"), pushDocument);

	System.out.println("After updating document you will find email added to headers.To array");
	MongoUtil.printFindIterableDocuments(messagesCollection.find(Filters.eq("headers.Message-ID", "<8147308.1075851042335.JavaMail.evans@thyme>")));
  }
}
