package alex.com.android_1.http;

import java.util.List;

import alex.com.android_1.PhotoItem;

public interface NetworkResultListener {

    void onPhotoItemsCompleteCallback(List<PhotoItem> photoItems);

}
