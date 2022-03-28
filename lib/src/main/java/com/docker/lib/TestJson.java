package com.docker.lib;

import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject("{need_refresh:0}");
        try {
            JSONObject jsonObject1 = new JSONObject(
                    "{\"ext\": \"\\\"{\\\"need_refresh\\\":\\\"0\\\"}\\\"\"}"
            );
            JSONObject jsonObjectExt = jsonObject1.optJSONObject("ext");
            String extStr = jsonObject1.optString("ext");
            String substring = extStr.substring(1, extStr.length() - 1);
            JSONObject jsonObject2 = new JSONObject(substring);
            JSONObject jsonObject3 = new JSONObject(substring);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
