package com.abhishek.mongodb.tutorial.week1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Abhi on 5/30/2016.
 */
public class SparkRoutesApp {

  public static void main(String[] args) {
	Spark.get(new Route("/") {

	  @Override public Object handle(final Request request, final Response response) {
		return "Hello World\n";
	  }
	});

	Spark.get(new Route("/test") {

	  @Override public Object handle(final Request request, final Response response) {
		return "This is a test page\n";
	  }
	});

	//This is like Path variable
	Spark.get(new Route("/pathparam/:thing") {
	  @Override public Object handle(final Request request, final Response response) {
		return request.params(":thing");

	  }
	});

	//This is like Queryparam.
	Spark.get(new Route("queryparam") {
	  @Override public Object handle(Request request, Response response) {
		return request.queryParams("q");
	  }
	});
  }
}
