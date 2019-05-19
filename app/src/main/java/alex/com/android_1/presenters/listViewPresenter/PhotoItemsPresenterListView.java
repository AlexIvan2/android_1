//package alex.com.android_1.presenters.listViewPresenter;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.GridView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//
//import alex.com.android_1.R;
//import alex.com.android_1.catalog.CatalogGridViewActivity;
//import alex.com.android_1.interfaces.NetworkingManager;
//import alex.com.android_1.interfaces.PhotoItem;
//import alex.com.android_1.interfaces.PhotoItemsPresenter;
//import alex.com.android_1.catalog.ViewAdapter;
//import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;
//
//public class PhotoItemsPresenterListView extends AppCompatActivity implements PhotoItemsPresenterCallback {
//
//    NetworkingManager networkingManager = null;
//    PhotoItemsPresenter presenter = null;
//
//
//    public enum ImgServices {
//        UNSPLASH,
//        GIPHY,
//        FAVORITES
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        showImgService(CatalogGridViewActivity.ImgServices.GIPHY);
//    }
//
//    @Override
//    public void setupWithPhotoItems(Activity activity, List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback) {
//        ListView resultView = activity.findViewById(R.id.catalogListView);
////        ViewAdapter adapter = new ViewAdapter(activity, R.layout.photo_item, photoItems);
////        resultView.setAdapter(adapter);
//        setOnTouchListener(activity, resultView);
//        resultView.setOnItemClickListener((parent, view, position, id) -> callback.onItemSelected(photoItems.get(position)));
//    }
//
//    @Override
//    public void setupWithPhotoItems(List<PhotoItem> photoItems, Activity activity, PhotoItemsPresenterCallback callback) {
//
//    }
//
//    @Override
//    public void updateWithItems(List<PhotoItem> photoItems) {
//
//    }
//
//    private void setOnTouchListener(Activity activity, ListView view) {
//        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
//            PhotoItem photoItem = (PhotoItem) adapterView.getItemAtPosition(position);
//
//            Toast toast = Toast.makeText(activity.getApplicationContext(),
//                    StringUtils.isNotBlank(photoItem.getLocation()) ? photoItem.getLocation() : "Unknown location",
//                    Toast.LENGTH_SHORT);
//            toast.show();
//        });
//    }
//
//    @Override
//    public void onItemSelected(PhotoItem photoItem) {
//
//    }
//
//    @Override
//    public void onItemToggleFavorite(PhotoItem item) {
//
//    }
//
//    @Override
//    public void onLastItemReach(int position) {
//
//    }
//}
