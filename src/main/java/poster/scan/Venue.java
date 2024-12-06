package poster.scan;

import org.json.JSONObject;

public class Venue extends DBEntity {
    String dbName;
    String stylizedName;
    Boolean addressProtected;
    String address;
    String website;

    public Venue(JSONObject inputJson) {
        if (!((String) inputJson.get("db_name")).contains(" ")) {
            this.dbName = (String) inputJson.get("db_name");
        }
        this.stylizedName = (String) inputJson.get("db_name");
        if (inputJson.get("address_protected").equals("true") || inputJson.get("address_protected").equals("false")) {
            this.addressProtected = Boolean.parseBoolean((String) inputJson.get("address_protected"));
        }
        this.address = (String) inputJson.get("address");
        if (((String) inputJson.get("website")).matches(URL_MATCH)) {
            this.website = (String) inputJson.get("website");
        }
    }

    @Override
    public int postData() {
        JSONObject body = produceJSON();
        return 200;
    }

    @Override
    public Boolean checkData() {
        if (stylizedName != null) {
            if (dbName == null) {
                this.dbName = stylizedName;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject produceJSON() {
        JSONObject jsonObject = new JSONObject();
        if (checkData()) {
            jsonObject.put("stylized_name", stylizedName);
            jsonObject.put("db_name", dbName);
            jsonObject.put("website", website);
            jsonObject.put("address_protected", addressProtected);
            jsonObject.put("address", address);
            return jsonObject;
        } else {
            System.out.println("Insufficient Data");
            return jsonObject;
        }
    }

    public String getDBName() {
        return this.dbName;
    }
}
