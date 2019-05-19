package alex.com.android_1.catalog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.ShareActivityWithFragment;
import alex.com.android_1.dataSources.giphy.GiphyNetworkingManager;
import alex.com.android_1.interfaces.NetworkingResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.dataSources.unsplash.UnsplashNetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;
import alex.com.android_1.presenters.gridViewPresenter.PhotoItemsPresenterGridView;

public class CatalogGridViewActivity extends Activity implements PhotoItemsPresenterCallback {

    NetworkingManager networkingManager = null;
    PhotoItemsPresenter presenter = null;

    public enum ImgServices {
        UNSPLASH,
        GIPHY,
        FAVORITES
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showImgService(ImgServices.GIPHY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);

        final MenuItem favoriteMenuItem = menu.findItem(R.id.action_show_favotites);
        favoriteMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImgService(ImgServices.FAVORITES);
            return true;
        });

        final MenuItem showUnsplashMenuItem = menu.findItem(R.id.action_show_unslash);
        showUnsplashMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImgService(ImgServices.UNSPLASH);
            return true;
        });

        return true;
    }

    private void showImgService(ImgServices service) {
        switch (service) {
            case GIPHY:
                networkingManager = new GiphyNetworkingManager();
                break;
            case UNSPLASH:
                networkingManager = new UnsplashNetworkingManager();
                break;
            case FAVORITES:
//                networkingManager = new NetworkingManagerLocal();
                break;
        }

        presenter = new PhotoItemsPresenterGridView();
        networkingManager.getPhotoItems(photoItems ->
                runOnUiThread(()-> presenter.setupWithPhotoItems(photoItems,this, this))
        );
    }


    @Override
    public void onItemSelected(PhotoItem photoItem) {
        Intent intent = ShareActivityWithFragment.buildIntent(this, photoItem);
        startActivity(intent);
    }

    @Override
    public void onItemToggleFavorite(PhotoItem item) {

    }

    @Override
    public void onLastItemReach(int position) {

    }
//
//    @Override
//    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemUnsplashes) {
//        runOnUiThread(() -> presenter.setupWithPhotoItems(this, photoItemUnsplashes, this));
//    }
}
