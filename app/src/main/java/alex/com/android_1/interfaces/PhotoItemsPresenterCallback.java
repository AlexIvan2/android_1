package alex.com.android_1.interfaces;

public interface PhotoItemsPresenterCallback {

    void onItemSelected(PhotoItem photoItem);
    void onItemToggleFavorite(PhotoItem item);
    void onLastItemReach(int position);

}
