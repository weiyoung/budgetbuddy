package model;

import org.json.JSONException;
import org.json.JSONObject;

public class DateTimeParser {

    public void parse(String jsonData) throws JSONException {
        JSONObject data = new JSONObject(jsonData);
        parseDate(data);
    }

    private void parseDate(JSONObject data) throws JSONException{
        String date = data.getString("formatted");
        System.out.println("Current date and time: " + date);
    }

}
