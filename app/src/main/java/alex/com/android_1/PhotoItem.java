package alex.com.android_1;

import java.util.Map;

public class PhotoItem {

    Map<String, String> urls;
    User user;

    public String getImgUrl() {
        return urls.get("regular");
    }

    public String getUser() {
        return user.name;
    }

    public String getLocation() {
        return user.location;
    }

    private class User {

        private String name;
        private String location;

    }

}
