package poster.scan;
import org.json.JSONObject;

public abstract class DBEntity {
    static String URL_MATCH = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    static String EMAIL_MATCH = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    static String DATE_MATCH = "^\\d{4}-\\d{2}-\\d{2}$";
    static String TIME_MATCH = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
    public abstract int postData();
    public abstract Boolean checkData();
    public abstract JSONObject produceJSON();
}
