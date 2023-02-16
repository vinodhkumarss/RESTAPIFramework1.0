package com.mycom.learn.data;

import com.mycom.learn.vo.AddMap;
import com.mycom.learn.vo.DeletePlace;
import com.mycom.learn.vo.Location;

import java.util.ArrayList;

public class TestDataBuild {

    public static AddMap add_place(String name, String address, String language){
        // Add Payload
        AddMap ap = new AddMap();
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        ap.setLocation(l);
        ap.setAccuracy(50);
        ap.setName(name);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setAddress(address);
        ArrayList<String> typ = new ArrayList<>();
        typ.add("shoe park");
        typ.add("shop");
        ap.setWebsite("http://google.com");
        ap.setLanguage(language);
        return ap;
    }

    public static DeletePlace delete_payload(String place_id){
        DeletePlace dp = new DeletePlace();
        dp.setPlace_id(place_id);
        return dp;
    }
}
