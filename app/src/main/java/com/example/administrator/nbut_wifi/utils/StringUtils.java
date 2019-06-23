package com.example.administrator.nbut_wifi.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class StringUtils {

   public static String  getLoginInfo(String info) throws JSONException {


       JSONObject jsonObject = JSON.parseObject(info);

       final String message = jsonObject.getString("message");

       return message;
   }


}
