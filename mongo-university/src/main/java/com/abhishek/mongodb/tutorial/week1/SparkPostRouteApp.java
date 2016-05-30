package com.abhishek.mongodb.tutorial.week1;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Abhishek on 5/30/2016.
 */
public class SparkPostRouteApp {
  public static void main(String[] args) {
	final Configuration configuration = new Configuration();
	configuration.setClassForTemplateLoading(SparkPostRouteApp.class, "/");

	Spark.get(new Route("/") {
	  @Override
	  public Object handle(final Request request, final Response response) {
		try {

		  Map<String, Object> fruitsMap = new HashMap<String, Object>();
		  fruitsMap.put("courses",
				  Arrays.asList("Core Java", "Spring", "Hibernate", "MongoDB"));

		  Template fruitPickerTemplate =
				  configuration.getTemplate("coursePicker.ftl");
		  StringWriter writer = new StringWriter();
		  fruitPickerTemplate.process(fruitsMap, writer);
		  return writer;

		} catch (Exception e) {
		  halt(500);
		  return null;
		}
	  }
	});

	Spark.post(new Route("/favorite_course") {
	  @Override
	  public Object handle(final Request request, final Response response) {
		final String course = request.queryParams("course");
		if (course == null) {
		  return "Please pick at lease one course ?";
		}
		else {
		  return "Your favorite course is " + course;
		}
	  }
	});
  }
}
