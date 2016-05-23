package com.abhishek.mongodb.aggregate.group;

import java.util.ArrayList;
import java.util.Date;
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
 * @link https://docs.mongodb.com/manual/reference/operator/aggregation/lookup/#pipe._S_lookup
 */
public class MongoLookupApp {

  //This boolean should be set to false if testData is already setup.
  public static boolean isTestDataSetup = false;

  public static void main(String arg[]) {

	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	setupTestData(mongoDatabase);

	MongoCollection<Document> orderCollection = mongoDatabase.getCollection("orders");

	Document lookupDocument = new Document("$lookup", new Document("from", "inventories").append("localField", "item").append("foreignField","sku").append("as","inventory_docs"));

	List<Document> aggregateListDocuments = new ArrayList<>();
	aggregateListDocuments.add(lookupDocument);

	MongoUtil.printAggregateIterableDocuments(orderCollection.aggregate(aggregateListDocuments));


  }

  public static void setupTestData(MongoDatabase mongoDatabase){
	if(isTestDataSetup){
	  MongoCollection<Document> orderCollection = mongoDatabase.getCollection("orders");
	  setOrdersDataForLookup(orderCollection);

	  MongoCollection<Document> inventoryCollection = mongoDatabase.getCollection("inventories");
	  setInventoriesDataForLookup(inventoryCollection);

	}
  }
  public static void setInventoriesDataForLookup(MongoCollection mongoCollection){
	mongoCollection.insertOne(createInventoryDocument("abc", "product 1", 120));
	mongoCollection.insertOne(createInventoryDocument("def", "product 2", 80));
	mongoCollection.insertOne(createInventoryDocument("ijk", "product 3", 60));
	mongoCollection.insertOne(createInventoryDocument("jkl", "product 4", 70));
	mongoCollection.insertOne(createInventoryDocument(null, "Incomplete", null));
	mongoCollection.insertOne(createInventoryDocument(null, null, null));

  }

  public static void setOrdersDataForLookup(MongoCollection mongoCollection){
	mongoCollection.insertOne(createOrdersDocument("abc",12.0,2));
	mongoCollection.insertOne(createOrdersDocument("jkl",20.0,1));
  }


  public static Document createInventoryDocument(String sku, String description, Integer instock){
	Document inventoryDocument = new Document();
	inventoryDocument.append("sku", sku);
	inventoryDocument.append("description", description);
	inventoryDocument.append("instock", instock);

	return  inventoryDocument;
  }

  public static Document createOrdersDocument(String item, Double price, Integer quantity){
	Document orderDocument = new Document();
	orderDocument.append("item", item);
	orderDocument.append("price", price);
	orderDocument.append("quantity", quantity);

	return orderDocument;
  }

}
