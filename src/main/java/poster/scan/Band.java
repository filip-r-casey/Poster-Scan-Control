package poster.scan;

import org.json.JSONObject;

public class Band extends DBEntity {
    String dbName;
    String stylizedName;
    String genre;
    String biography;
    String website;
    String musicLink;
    String contactEmail;
    String instagramUsername;
    String twitterUsername;
    String tiktokUsername;
    Boolean isActive;

    public Band(JSONObject inputJson) {
        if (!((String) inputJson.get("db_name")).contains(" ")) {
            this.dbName = (String) inputJson.get("db_name");
        }
        this.stylizedName = (String) inputJson.get("stylized_name");
        this.genre = (String) inputJson.get("genre");
        this.biography = (String) inputJson.get("biography");
        if (((String) inputJson.get("website")).matches(URL_MATCH)) {
            this.website = (String) inputJson.get("website");
        }
        if (((String) inputJson.get("music_link")).matches(URL_MATCH)) {
            this.musicLink = (String) inputJson.get("musicLink");
        }
        if (((String) inputJson.get("contact_email")).matches(EMAIL_MATCH)) {
            this.contactEmail = (String) inputJson.get("contactEmail");
        }
        this.instagramUsername = (String) inputJson.get("instagram_username");
        this.twitterUsername = (String) inputJson.get("twitter_username");
        this.tiktokUsername = (String) inputJson.get("tiktok_username");
        if (inputJson.get("is_active").equals("true") || inputJson.get("is_active").equals("false")) {
            this.isActive = Boolean.parseBoolean((String) inputJson.get("is_active"));
        }
    }

    @Override
    public int postData() {
        JSONObject body = produceJSON();
        return 200;
    }

    @Override
    public Boolean checkData() {
        if (stylizedName != null && genre != null) {
            if (dbName == null) {
                this.dbName = stylizedName;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject produceJSON(){
        JSONObject jsonObject = new JSONObject();
        if (checkData()) {
            jsonObject.put("stylized_name", stylizedName);
            jsonObject.put("genre", genre);
            jsonObject.put("db_name", dbName);
            jsonObject.put("biography", biography);
            jsonObject.put("website", website);
            jsonObject.put("music_link", musicLink);
            jsonObject.put("contact_email", contactEmail);
            jsonObject.put("instagram_username", instagramUsername);
            jsonObject.put("twitter_username", twitterUsername);
            jsonObject.put("tiktok_username", tiktokUsername);
            jsonObject.put("is_active", isActive);
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
