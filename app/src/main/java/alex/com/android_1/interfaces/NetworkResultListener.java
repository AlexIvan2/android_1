package alex.com.android_1.interfaces;

import java.util.List;

import alex.com.android_1.dataSources.unsplash.PhotoItemUnsplash;

public interface NetworkResultListener {

    void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemUnsplashes);

}
