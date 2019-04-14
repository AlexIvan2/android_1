package alex.com.android_1.dataSources.unsplash;

import java.util.Map;

import alex.com.android_1.interfaces.PhotoItem;

public class PhotoItemUnsplash implements PhotoItem {

    Map<String, String> urls;
    User user;

    @Override
    public String getImgUrl() {
        return this.urls.get("regular");
    }

    @Override
    public String getUserName() {
        return this.user.name;

    }

    @Override
    public String getLocation() {
        return user.location;
    }

    class User {

        String name;
        String location;

    }

}
