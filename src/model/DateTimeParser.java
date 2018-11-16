package model;

import org.json.JSONException;
import org.json.JSONObject;

public class DateTimeParser {

    // REQUIRES: a JSON file as a String
    // MODIFIES:
    // EFFECTS: casts into JSONObject and calls parseDate
    public void parse(String jsonData) throws JSONException {
        JSONObject data = new JSONObject(jsonData);
        parseDate(data);
    }

    // REQUIRES: JSONObject
    // MODIFIES:
    // EFFECTS: prints "countryName", "zoneName", and "formatted"
    private void parseDate(JSONObject data) throws JSONException{
        System.out.println("Country: " + data.getString("countryName"));
        System.out.println("Zone: " + data.getString("zoneName"));
        System.out.println("Current date and time: " + data.getString("formatted"));
    }

}
