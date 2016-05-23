package com.abhishek.mongodb.aggregate.bool;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/23/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/and/#exp._S_and
 */
public class MongoAndApp {

  public static void main(String arg[]) {

	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> inventoryCollection = mongoDatabase.getCollection("inventories");

	Document projectDocument = new Document();
	projectDocument.append("sku", 1);
	projectDocument.append("instock", 1);

	List<Document> andExpressions = new ArrayList<>();
	List gtArguments = new ArrayList();
	gtArguments.add("$instock");
	gtArguments.add(50);

	Document expOne = new Document("$gt", gtArguments);
	andExpressions.add(expOne);

	List ltArguments = new ArrayList();
	ltArguments.add("$instock");
	ltArguments.add(80);

	Document expTwo = new Document("$lt", ltArguments);
	andExpressions.add(expTwo);

	projectDocument.append("result" , new Document("$and", andExpressions));

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(new Document("$project", projectDocument));

	MongoUtil.printAggregateIterableDocuments(inventoryCollection.aggregate(aggregateListDocuments));

  }
}
