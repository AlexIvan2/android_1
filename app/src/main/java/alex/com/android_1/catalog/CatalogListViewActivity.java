//package alex.com.android_1.catalog;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//import java.util.List;
//
//import alex.com.android_1.R;
//import alex.com.android_1.ShareActivity;
//import alex.com.android_1.dataSources.giphy.GiphyNetworkingManager;
//import alex.com.android_1.interfaces.NetworkingResultListener;
//import alex.com.android_1.interfaces.NetworkingManager;
//import alex.com.android_1.interfaces.PhotoItem;
//import alex.com.android_1.interfaces.PhotoItemsPresenter;
//import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;
//import alex.com.android_1.presenters.listViewPresenter.PhotoItemsPresenterListView;
//
//public class CatalogListViewActivity extends AppCompatActivity implements NetworkingResultListener, PhotoItemsPresenterCallback {
//
//    private PhotoItemsPresenter presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_catalog);
//        presenter = new PhotoItemsPresenterListView();
//        NetworkingManager networkingManager = new GiphyNetworkingManager(this);
//        networkingManager.getImages();
//    }
//
//    @Override
//    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemsGiphy) {
//        runOnUiThread(() -> presenter.setupWithPhotoItems(this, photoItemsGiphy, this));
//    }
//
//    @Override
//    public void onItemSelected(PhotoItem photoItem) {
//        Intent intent = ShareActivity.buildIntent(this, photoItem);
//        startActivity(intent);
//    }
//}
