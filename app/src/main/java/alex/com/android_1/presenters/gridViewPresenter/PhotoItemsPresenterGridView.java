package alex.com.android_1.presenters.gridViewPresenter;

import android.app.Activity;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.catalog.ViewAdapter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {

    List<PhotoItem> photoItems;
    private ViewAdapter adapter;

    @Override
    public void setupWithPhotoItems(List<PhotoItem> photoItems, Activity activity, PhotoItemsPresenterCallback callback) {
        this.photoItems = photoItems;
        GridView gridView = new GridView(activity);
        activity.setContentView(gridView);
        adapter = new ViewAdapter(activity, R.layout.photo_item, photoItems, callback);
        gridView.setAdapter(adapter);

//        setOnTouchListener(activity, gridView);
        gridView.setOnItemClickListener((parent, view, position, id) -> callback.onItemSelected(photoItems.get(position)));
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    callback.onLastItemReach(totalItemCount);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
        });
    }

    @Override
    public void updateWithItems(List<PhotoItem> photoItems) {
        this.photoItems = photoItems;
        adapter.notifyDataSetChanged();
    }
}
