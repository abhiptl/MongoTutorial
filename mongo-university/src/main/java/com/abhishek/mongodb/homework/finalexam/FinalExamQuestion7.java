package com.abhishek.mongodb.homework.finalexam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.abhishek.mongodb.constant.MongoConstant;
import com.abhishek.mongodb.constant.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * Created by Abhishek on 08-Jul-16.
 *
 * Import data using following command
 *
 * mongoimport --db finalexam --collection images --drop --file images.json
   mongoimport --db finalexam --collection albums --drop --file albums.json
 */
public class FinalExamQuestion7 {

  public static void main(String[] args) {
	MongoClient mongoClient = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("finalexam");

	MongoCollection<Document> imagesCollection = mongoDatabase.getCollection("images");
	MongoCollection<Document> albumsCollection = mongoDatabase.getCollection("albums");

	ArrayList<Document> totalImages = imagesCollection.find().into(new ArrayList<Document>());
	System.out.println("Total Images :"+totalImages.size());

	ArrayList<Document> totalAlbums = albumsCollection.find().into(new ArrayList<Document>());
	System.out.println("Total Albums :"+totalAlbums.size());


	ArrayList<Document> sunrisesImagesBefore = imagesCollection.find(Filters.eq("tags", "sunrises")).into(new ArrayList<Document>());

	System.out.println("Total number of images before remove tag as sunrises :"+sunrisesImagesBefore.size());

	HashSet<Integer> nonOrphanImages = new HashSet<>();
	for(Document albumDocument : totalAlbums){
	  ArrayList<Integer> images = albumDocument.get("images", ArrayList.class);
	  nonOrphanImages.addAll(images);
	}

	List<Integer> imagesToBeDelete = new ArrayList<>();
	for(Document imageDocument : totalImages){
	  Integer imageId = imageDocument.getInteger("_id");
	  if(!nonOrphanImages.contains(imageId)){
		imagesToBeDelete.add(imageId);
		imagesCollection.deleteOne(Filters.eq("_id", imageId));
	  }
	}

	System.out.println("Total Orphan Images :"+imagesToBeDelete.size());

	ArrayList<Document> sunrisesImagesAfter = imagesCollection.find(Filters.eq("tags", "sunrises")).into(new ArrayList<Document>());

	System.out.println("Total number of images After remove tag as sunrises :"+sunrisesImagesAfter.size());
  }
}
