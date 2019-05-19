package alex.com.android_1.interfaces;

import java.io.Serializable;

public interface PhotoItem extends Serializable {

    String getImgUrl();
    String getUserName();
    String getLocation();

    // ORM
    void saveToDatabase();
    void deleteFromDatabase();
    boolean isSavedToDatabase();
}
