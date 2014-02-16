package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Naveen
 * Date: 9/1/14
 * Time: 2:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args){
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class,"/");

        //Calling a static method
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                try{
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name","Freemarker");
                    helloTemplate.process(helloMap, writer);
                    System.out.println(writer);
                }
                catch (Exception e)        {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
