package com.example.administrator.nbut_wifi;

import org.json.JSONException;
import org.json.JSONObject;

public class StringUtils {

   public static String  getLoginInfo(String info) throws JSONException {
       JSONObject jsonObject = new JSONObject(info);
       String message = jsonObject.getString("message");


    return message;
   }


}
