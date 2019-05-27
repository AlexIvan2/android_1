package alex.com.android_1.dataSources.giphy;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.Map;

import alex.com.android_1.interfaces.PhotoItem;
@Table
public class PhotoItemGiphy implements PhotoItem {

    // that the main pain
    // because DB need to have its own ID, it conflict with ID that we receive from server
    // In order to overcome that, we need to manually resolve them
    @SerializedName("db_id") // ID that use local DB
    private transient Long id;

    @SerializedName("id") // ID that came from server
    private String imgID;

    String username;
    ImagesContainer images;

    private String imagesForORM;

    @Override
    public String getImgUrl() {
//        return images.get("downsized_medium").get("url");
        if (images == null) { // Because this DB does not know how to fully recreate object, we need to re-create its nested properties back manually
            this.images = new Gson().fromJson(this.imagesForORM, ImagesContainer.class);
        }

        return images.getMediumSize();
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
        // before save - convert inner objects to string
        this.imagesForORM = images.toString();
        SugarRecord.save(this);
    }

    @Override
    public void deleteFromDatabase() {
        // A small query is needed because of conflicted id properties
        SugarRecord.deleteAll(PhotoItemGiphy.class,"img_ID = ?", this.imgID);
    }

    @Override
    public boolean isSavedToDatabase() {
        return SugarRecord.find(PhotoItemGiphy.class, "img_ID = ?", this.imgID).size() > 0;

    }

    public class ImagesContainer implements Serializable {

        DownsizedMedium downsized_medium;

        String getMediumSize() {
            return downsized_medium.url;
        }

        public String toString() {
            // convert to a String object, because it could be saved to database without much hassle
            return new Gson().toJson(this);
        }
    }

    public class DownsizedMedium implements Serializable {
        String url;
    }
}
