package com.abhishek.mongodb.tutorial.week1;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Created by Abhishek on 5/30/2016.
 */
public class FreemarkerHelloWorld {

  public static void main(String[] args) {
	Configuration configuration = new Configuration();
	configuration.setClassForTemplateLoading(FreemarkerHelloWorld.class , "/");

	try{
	  Template helloTemplate = configuration.getTemplate("hello.ftl");

	  StringWriter stringWriter = new StringWriter();

	  Map<String, Object> helloMap = new HashMap<>();
	  helloMap.put("name", "FreeMarker");

	  helloTemplate.process(helloMap, stringWriter);

	  System.out.println(stringWriter);
	}
	catch(Exception e){
	  e.printStackTrace();
	}

  }
}
