package alex.com.android_1.interfaces;

public interface NetworkingManager {

    void getPhotoItems(NetworkingResultListener result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result);

}
