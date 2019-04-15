package alex.com.android_1.catalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.ShareActivity;
import alex.com.android_1.ShareActivityWithFragment;
import alex.com.android_1.interfaces.NetworkResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.dataSources.unsplash.UnsplashNetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;
import alex.com.android_1.presenters.gridViewPresenter.PhotoItemsPresenterGridView;

public class CatalogGridViewActivity extends AppCompatActivity implements NetworkResultListener, PhotoItemsPresenterCallback {

    private PhotoItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_catalog);
//        PhotoItemsPresenter photoItemsPresenter = new PhotoItemsPresenterListView();
        presenter = new PhotoItemsPresenterGridView();
        NetworkingManager networkingManager = new UnsplashNetworkingManager(this);
        networkingManager.getImages();
    }

    @Override
    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemUnsplashes) {
        runOnUiThread(() -> presenter.showPhotoItems(this, photoItemUnsplashes, this));
    }

    @Override
    public void onItemSelected(PhotoItem photoItem) {
        Intent intent = ShareActivityWithFragment.buildIntent(this, photoItem);
        startActivity(intent);

    }
}
