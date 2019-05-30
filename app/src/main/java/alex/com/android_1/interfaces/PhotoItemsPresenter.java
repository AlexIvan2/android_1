package alex.com.android_1.interfaces;

import android.app.Activity;

import java.util.List;

public interface PhotoItemsPresenter {

    void showPhotoItems(List<PhotoItem> photoItems, Activity activity, PhotoItemsPresenterCallback callback);
    void updateWithItems(List<PhotoItem> photoItems);

}
