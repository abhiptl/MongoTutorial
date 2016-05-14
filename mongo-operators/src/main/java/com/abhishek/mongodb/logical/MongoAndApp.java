package com.abhishek.mongodb.logical;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Abhishek on 5/14/2016.
 */
public class MongoAndApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find All Restaurants with zipcode=10462 and cuisine=Bakery");

	List<Document> listExpressions = new ArrayList<>();
	Document zipCodeExp = new Document("address.zipcode", "10462");
	listExpressions.add(zipCodeExp);

	Document cuisineExp = new Document("cuisine", "Bakery");
	listExpressions.add(cuisineExp);


	Document andExpression = new Document("$and", listExpressions);
	System.out.println("AND Expression: "+andExpression.toJson());

	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(andExpression));

	System.out.println("Find All Restaurants with (zipcode=10462 and cuisine=Bakery) or (building=1007 and borough=Bronx)");

	Document zipExp = new Document("address.zipcode", "10462").append("cuisine", "Bakery");
	Document buildingExp = new Document("address.building", "1007").append("borough", "Bronx");

	List<Document> listExpressionsForOr = new ArrayList<>();
	listExpressionsForOr.add(buildingExp);
	listExpressionsForOr.add(zipExp);

	Document orExpression = new Document("$or", listExpressionsForOr);
	System.out.println("OR Expression: "+orExpression.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(orExpression));

  }
}
