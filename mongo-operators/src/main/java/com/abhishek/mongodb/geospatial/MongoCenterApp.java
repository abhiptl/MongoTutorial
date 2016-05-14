package com.abhishek.mongodb.geospatial;

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
 *
 *@link https://docs.mongodb.com/manual/reference/operator/query/center/
 */
public class MongoCenterApp {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.DB_NAME);

	MongoCollection<Document> restaurantsCollection = mongoDatabase.getCollection("restaurants");

	System.out.println("Find All restaurants in 1 units radius center at [-73.856077, 40.848447] in zipcode=10462");

	ArrayList<Double> centerCoordinate = new ArrayList<>();
	centerCoordinate.add(-73.856077);
	centerCoordinate.add(40.848447);

	List centerDocumentList = new ArrayList<>();
	centerDocumentList.add(centerCoordinate);

	//Adding Radiuss
	centerDocumentList.add(1);

	Document centerDocument = new Document("$center", centerDocumentList);
	Document geoWithinDocument = new Document("$geoWithin", centerDocument);

	Document locationDocument = new Document("address.coord", geoWithinDocument).append("address.zipcode", "10462");

	System.out.println("GeoWithin Expression: "+locationDocument.toJson());
	MongoUtil.printFindIterableDocuments(restaurantsCollection.find(locationDocument));

  }
}
