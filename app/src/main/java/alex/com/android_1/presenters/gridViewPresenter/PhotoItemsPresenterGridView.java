package alex.com.android_1.presenters.gridViewPresenter;

import android.app.Activity;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.catalog.ViewAdapter;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {

    @Override
    public void showPhotoItems(Activity activity, List<PhotoItem> photoItemUnsplashList) {
        GridView resultView = activity.findViewById(R.id.catalogGridView);
        ViewAdapter adapter = new ViewAdapter(activity, R.layout.photo_item, photoItemUnsplashList);
        resultView.setAdapter(adapter);
        setOnTouchListener(activity, resultView);
    }

    private void setOnTouchListener(Activity activity, GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            PhotoItem photoItem = (PhotoItem) adapterView.getItemAtPosition(position);

            Toast toast = Toast.makeText(activity.getApplicationContext(),
                    StringUtils.isNotBlank(photoItem.getLocation()) ? photoItem.getLocation() : "Unknown location",
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}
