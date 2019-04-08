package alex.com.android_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import alex.com.android_1.dataSources.unsplash.PhotoItemUnsplash;
import alex.com.android_1.interfaces.NetworkResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.dataSources.unsplash.UnsplashNetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.presenters.GridViewAdapter;
import alex.com.android_1.presenters.PhotoItemsPresenterGridView;

public class CatalogActivity extends AppCompatActivity implements NetworkResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        PhotoItemsPresenter photoItemsPresenter = new PhotoItemsPresenterGridView();
        NetworkingManager networkingManager = new UnsplashNetworkingManager(this);
        networkingManager.getImages();
    }

    private void setOnTouchListener(GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            PhotoItemUnsplash photoItemUnsplash = (PhotoItemUnsplash) adapterView.getItemAtPosition(position);
//            Toast toast = Toast.makeText(getApplicationContext(), photoItemUnsplash.getLocation(), Toast.LENGTH_SHORT);
//            toast.show();
        });
    }

    public void showPhotoItems(List<PhotoItem> items) {
        GridView resultView = findViewById(R.id.catalogView);
        GridViewAdapter adapter = new GridViewAdapter(this, R.layout.car_item, items);
        resultView.setAdapter(adapter);
        setOnTouchListener(resultView);
    }

    @Override
    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItemUnsplashes) {
        runOnUiThread(() -> showPhotoItems(photoItemUnsplashes));
    }
}
