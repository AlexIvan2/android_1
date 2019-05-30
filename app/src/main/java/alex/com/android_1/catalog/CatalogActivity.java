package alex.com.android_1.catalog;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import alex.com.android_1.R;
import alex.com.android_1.ShareActivityWithFragment;
import alex.com.android_1.dataSources.giphy.GiphyNetworkingManager;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.dataSources.unsplash.UnsplashNetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;
import alex.com.android_1.presenters.gridViewPresenter.PhotoItemsPresenterGridView;
import alex.com.android_1.presenters.recyclerViewPresenters.PhotoItemsPresenterRecyclerView;

import static android.os.Environment.getExternalStorageDirectory;

public class CatalogActivity extends Activity implements PhotoItemsPresenterCallback {

    private static String IMAGE_PROVIDER_KEY = "IMAGE_PROVIDER_KEY";
    private static String PRESENTER_KEY = "PRESENTER_KEY";

    NetworkingManager networkingManager = null;
    PhotoItemsPresenter presenter = new PhotoItemsPresenterRecyclerView();

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
        favoriteMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        favoriteMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImgService(ImgServices.FAVORITES);
            return true;
        });

        final MenuItem showUnsplashMenuItem = menu.findItem(R.id.action_show_unslash);
        showUnsplashMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        showUnsplashMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImgService(ImgServices.UNSPLASH);
            return true;
        });

        final MenuItem showGiphyMenuItem = menu.findItem(R.id.action_show_giphy);
        showGiphyMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        showGiphyMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImgService(ImgServices.GIPHY);
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

        getImages();
    }

    private void getImages() {
        networkingManager.getPhotoItems(photoItems ->
                runOnUiThread(()-> presenter.showPhotoItems(photoItems,this, this))
        );
    }


    @Override
    public void onItemSelected(PhotoItem photoItem) {
        Intent intent = ShareActivityWithFragment.buildIntent(this, photoItem);
        startActivity(intent);
    }

    @Override
    public void onItemToggleFavorite(PhotoItem item) {
        if (item.isSavedToDatabase()) {
            item.deleteFromDatabase();
        } else {
            item.saveToDatabase();
        }
    }

    @Override
    public void onLastItemReach(int position) {
        networkingManager.fetchNewItemsFromPosition(position, photoItems -> {
            runOnUiThread(()-> presenter.updateWithItems(photoItems));
        });
    }

    // *****************************************************
    // *****************************************************
    // ************************ ORM ************************
    // *****************************************************
    // *****************************************************

    private void testFavoriteORM(PhotoItem item) {

        if(item.isSavedToDatabase()) {
            item.deleteFromDatabase();
        } else {
            item.saveToDatabase();
        }
    }

    private void testWriteToExternalFile() {
        File path = getExternalStorageDirectory();
//        dont forget to add <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

        if(isStoragePermissionGranted()) { // check if storage is permission is granted
            File file = new File(path, "my_awesome_file.txt"); // file to write
            try {
                FileOutputStream stream = new FileOutputStream(file); // open stream to write
                stream.write("EXTERNAL: Today is a great day!".getBytes()); // try to write
                stream.close(); // close stream
            } catch (Exception ex) {
                Log.v("i",ex.getLocalizedMessage()); // if something went wrong - print error
            }
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Need to ask only after API 23
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Storage","Permission is granted");
                return true;
            } else {
                Log.v("Storage","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Storage","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){ // check permission result
            Log.v("STORAGE","Permission: "+permissions[0]+ "was "+grantResults[0]);
            testWriteToExternalFile(); // execute work
        }
    }
//
//    @Override
//    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemUnsplashes) {
//        runOnUiThread(() -> presenter.showPhotoItems(this, photoItemUnsplashes, this));
//    }
}