package alex.com.android_1.dataSources.giphy;

import java.util.Map;

import alex.com.android_1.interfaces.PhotoItem;

public class PhotoItemGiphy implements PhotoItem {

    String username;
    Map<String, Map<String, String>> images;

    @Override
    public String getImgUrl() {
        return images.get("downsized_medium").get("url");
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getLocation() {
        return "";
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


}
