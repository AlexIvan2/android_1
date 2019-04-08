package alex.com.android_1.interfaces;

import android.app.Activity;

import java.util.List;

import alex.com.android_1.dataSources.unsplash.PhotoItemUnsplash;

public interface PhotoItemsPresenter {

    void showPhotoItems(Activity activity, List<PhotoItemUnsplash> photoItemUnsplashList);

}
