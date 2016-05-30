package com.abhishek.mongodb.tutorial.week1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Abhishek on 5/30/2016.
 */
public class SparkHelloWorld {

  public static void main(String[] args) {

	Spark.get(new Route("/") {
	  @Override
	  public Object handle(final Request request,
			  final Response response) {
		return "Hello World From Spark\n";
	  }
	});
  }
}
