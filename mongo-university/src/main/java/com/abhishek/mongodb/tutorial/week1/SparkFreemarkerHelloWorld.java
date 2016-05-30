package com.abhishek.mongodb.tutorial.week1;

import java.io.StringWriter;
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
public class SparkFreemarkerHelloWorld {

  public static void main(String[] args) {
	final Configuration configuration = new Configuration();
	configuration.setClassForTemplateLoading(FreemarkerHelloWorld.class , "/");

	Spark.get(new Route("/") {
	  @Override
	  public Object handle(final Request request,
			  final Response response) {
		StringWriter stringWriter = new StringWriter();
		try{
		  Template helloTemplate = configuration.getTemplate("hello.ftl");

		  Map<String, Object> helloMap = new HashMap<>();
		  helloMap.put("name", "FreeMarker");

		  helloTemplate.process(helloMap, stringWriter);

		  return  stringWriter;
		}
		catch(Exception e){
		  e.printStackTrace();
		}
		return stringWriter;
	  }
	});
  }
}
