package com.abhishek.mongodb.create;

import java.util.Calendar;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 6/5/2016.
 */
public class CreateLogApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> logCollection = mongoDatabase.getCollection("systemlogs");

	Document logDocument = new Document();
	Calendar logDate = Calendar.getInstance();
	logDate.set(2016,Calendar.APRIL,25,15,11,17);
	logDocument.append("logDate", logDate.getTime());

	logDocument.append("threadName", "RMI TCP Connection(5)-127.0.0.1");
	logDocument.append("level", "INFO");
	logDocument.append("qualifiedClass", "org.generationcp.middleware.hibernate.XADataSourceProperties.getPropertyValue");
	logDocument.append("lineNumber", 92);
	logDocument.append("logMessage", "Property value 'db.host' found in database.properties. Using value specifed in properties file 'localhost'.");

	logCollection.insertOne(logDocument);

	MongoUtil.printFindIterableDocuments(logCollection.find());
  }
}
