package alex.com.android_1.interfaces;

import android.app.Activity;

import java.util.List;

public interface PhotoItemsPresenter {

    void showPhotoItems(Activity activity, List<PhotoItem> photoItemUnsplashList, PhotoItemsPresenterCallback callback);

}
