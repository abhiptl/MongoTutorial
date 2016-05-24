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
 * Created by Abhishek on 5/24/2016.
 *
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/or/#exp._S_or
 */
public class MongoOrApp {

  public static void main(String arg[]) {

	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	System.out.println("Display inverntories where either sku is null or instock is less than 80");
	MongoCollection<Document> inventoryCollection = mongoDatabase.getCollection("inventories");

	Document projectDocument = new Document();
	projectDocument.append("sku", 1);
	projectDocument.append("instock", 1);

	List<Document> andExpressions = new ArrayList<>();
	List gtArguments = new ArrayList();
	gtArguments.add("$sku");
	gtArguments.add(null);

	Document expOne = new Document("$eq", gtArguments);
	andExpressions.add(expOne);

	List ltArguments = new ArrayList();
	ltArguments.add("$instock");
	ltArguments.add(80);

	Document expTwo = new Document("$lt", ltArguments);
	andExpressions.add(expTwo);

	projectDocument.append("result" , new Document("$or", andExpressions));

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(new Document("$project", projectDocument));

	MongoUtil.printAggregateIterableDocuments(inventoryCollection.aggregate(aggregateListDocuments));

  }

}
