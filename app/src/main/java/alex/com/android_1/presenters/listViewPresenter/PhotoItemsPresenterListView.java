package alex.com.android_1.presenters.listViewPresenter;

import android.app.Activity;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.catalog.ViewAdapter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;

public class PhotoItemsPresenterListView implements PhotoItemsPresenter {

    @Override
    public void showPhotoItems(Activity activity, List<PhotoItem> photoItemUnsplashList, PhotoItemsPresenterCallback callback) {
        ListView resultView = activity.findViewById(R.id.catalogListView);
        ViewAdapter adapter = new ViewAdapter(activity, R.layout.photo_item, photoItemUnsplashList);
        resultView.setAdapter(adapter);
        setOnTouchListener(activity, resultView);
        resultView.setOnItemClickListener((parent, view, position, id) -> callback.onItemSelected(photoItemUnsplashList.get(position)));
    }

    private void setOnTouchListener(Activity activity, ListView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            PhotoItem photoItem = (PhotoItem) adapterView.getItemAtPosition(position);

            Toast toast = Toast.makeText(activity.getApplicationContext(),
                    StringUtils.isNotBlank(photoItem.getLocation()) ? photoItem.getLocation() : "Unknown location",
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}
