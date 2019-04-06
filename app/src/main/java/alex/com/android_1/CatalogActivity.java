package alex.com.android_1;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import alex.com.android_1.http.NetworkResultListener;
import alex.com.android_1.http.NetworkingManager;
import alex.com.android_1.http.UnsplashNetworkingManager;


public class CatalogActivity extends AppCompatActivity implements NetworkResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        NetworkingManager networkingManager = new UnsplashNetworkingManager(this);
        networkingManager.getPhotoItems();
    }

    private void setOnTouchListener(GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            Resources res = getResources();
            String[] items = res.getStringArray(R.array.car_types);

            Toast toast = Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    public void showPhotoItems(List<PhotoItem> items) {
        GridView resultView = findViewById(R.id.catalogView);
        GridViewAdapter adapter = new GridViewAdapter(this, R.layout.car_item, items);
        resultView.setAdapter(adapter);
        setOnTouchListener(resultView);

    }

    @Override
    public void onPhotoItemsCompleteCallback(List<PhotoItem> photoItems) {
        runOnUiThread(() -> showPhotoItems(photoItems));
    }
}
