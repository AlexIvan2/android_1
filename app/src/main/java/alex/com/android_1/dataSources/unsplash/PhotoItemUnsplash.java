package alex.com.android_1.dataSources.unsplash;

import java.io.Serializable;
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

    @Override
    public void saveToDatabase() {

    }

    @Override
    public void deleteFromDatabase() {

    }

    @Override
    public boolean isSavedToDatabase() {
        return false;
    }

    class User implements Serializable {

        String name;
        String location;

    }

}
