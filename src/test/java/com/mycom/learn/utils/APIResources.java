package com.mycom.learn.utils;

public enum APIResources {
    AddPlaceAPI("maps/api/place/add/json"),
    GetPlaceAPI("maps/api/place/get/json"),
    DeletePlaceAPI("maps/api/place/delete/json");

    private String resource_name;

    APIResources (String resource_name){
        this.resource_name = resource_name;
    }

    public String getResource_name() {
        return resource_name;
    }
}
