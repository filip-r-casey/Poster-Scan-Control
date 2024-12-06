package poster.scan;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Show extends DBEntity {
    String title;
    String date;
    String doorsTime;
    String showStartTime;
    Double cost;
    Integer ageRequirement;
    List<String> bands;
    String description;

    public Show(JSONObject inputJson) throws JsonProcessingException {
        if (inputJson.get("title") != null) {
            this.title = (String) inputJson.get("title");
        }
        if (((String) inputJson.get("date")).matches(DATE_MATCH)) {
            this.date = (String) inputJson.get("date");
        }
        if (((String) inputJson.get("doors_time")).matches(TIME_MATCH)) {
            this.doorsTime = (String) inputJson.get("doors_time");
        }
        if (((String) inputJson.get("show_start_time")).matches(TIME_MATCH)) {
            this.showStartTime = (String) inputJson.get("show_start_time");
        }
        if (!((String) inputJson.get("cost")).isEmpty()) {
            this.cost = Double.parseDouble((String) inputJson.get("cost"));
        } else {
            this.cost = 0.00;
        }
        this.ageRequirement = (Integer) inputJson.get("age_requirement");
        this.bands = new ArrayList<>();
        for (int i = 0; i < inputJson.getJSONArray("bands").length(); i ++) {
            this.bands.add((String) inputJson.getJSONArray("bands").get(i));
        }
        this.description = (String) inputJson.get("description");
    }

    @Override
    public int postData() {
        JSONObject body = produceJSON();
        return 200;
    }

    @Override
    public Boolean checkData() {
        return date != null && showStartTime != null && cost != null && ageRequirement != null && !bands.isEmpty();
    }

    @Override
    public JSONObject produceJSON(){
        JSONObject jsonObject = new JSONObject();
        if (checkData()) {
            jsonObject.put("title", title);
            jsonObject.put("date", date);
            jsonObject.put("doors_time", doorsTime);
            jsonObject.put("show_start_time", showStartTime);
            jsonObject.put("cost", cost);
            jsonObject.put("age_requirement", ageRequirement);
            jsonObject.put("bands", bands);
            jsonObject.put("description", description);
            return jsonObject;
        } else {
            System.out.println("Insufficient Data");
            return jsonObject;
        }
    }
}
