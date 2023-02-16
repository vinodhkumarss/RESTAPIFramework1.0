package com.mycom.learn.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification reqBuilder;
    public RequestSpecification request_specification() throws IOException {
        if(reqBuilder==null){
            // Logging
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            // Request Spec Builder
            reqBuilder = new RequestSpecBuilder().setBaseUri(get_property_value(load_property(),"baseUrl")).setContentType(ContentType.JSON)
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
        }
        return reqBuilder;
    }

    public Properties load_property() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Projects\\Java\\APITestFramework\\src\\test\\resources\\testconfig.properties");
        prop.load(fis);
        return prop;
    }

    public String get_property_value(Properties prop, String key){
        return prop.getProperty(key);
    }

    public static String get_json_value(Response response, String key){
        return new JsonPath(response.asString()).getString(key);
    }
}
