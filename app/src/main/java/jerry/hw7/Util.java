package jerry.hw7;

import com.google.gson.Gson;

public class Util {

    public static LiveCams GetLiveCamsFrom(String s) {
        Gson gson = new Gson();
        LiveCams liveCams = gson.fromJson(s, LiveCams.class);
        return liveCams;
    }
}
