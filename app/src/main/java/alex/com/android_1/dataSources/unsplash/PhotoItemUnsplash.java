package alex.com.android_1.dataSources.unsplash;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

import alex.com.android_1.interfaces.PhotoItem;

public class PhotoItemUnsplash implements PhotoItem {

    @SerializedName("db_id")
    private transient Long id = null;

    @SerializedName("id")
    private String imgID;

    URLs urls;
    User user;

    private String URLsFromORM;
    private String UserFromORM;

    public PhotoItemUnsplash() {}

    public String getImgUrl() {
        if (urls == null) {
            this.urls = new Gson().fromJson(this.URLsFromORM, URLs.class);
        }

        return this.urls.regular;
    }

    @Override
    public String getUserName() {
        if (user == null) {
            this.user = new Gson().fromJson(this.UserFromORM, User.class);
        }

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

    public class URLs implements Serializable {
        String regular;

        public String toString() {
            return new Gson().toJson(this);
        }

    }

}
