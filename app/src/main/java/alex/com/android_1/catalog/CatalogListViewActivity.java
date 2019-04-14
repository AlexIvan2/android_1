package alex.com.android_1.catalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.dataSources.giphy.GiphyNetworkingManager;
import alex.com.android_1.dataSources.unsplash.UnsplashNetworkingManager;
import alex.com.android_1.interfaces.NetworkResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.presenters.listViewPresenter.PhotoItemsPresenterListView;

public class CatalogListViewActivity extends AppCompatActivity implements NetworkResultListener {

    private PhotoItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_catalog);
        presenter = new PhotoItemsPresenterListView();
        NetworkingManager networkingManager = new GiphyNetworkingManager(this);
        networkingManager.getImages();
    }

    @Override
    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemsGiphy) {
        runOnUiThread(() -> presenter.showPhotoItems(this, photoItemsGiphy));
    }
}
