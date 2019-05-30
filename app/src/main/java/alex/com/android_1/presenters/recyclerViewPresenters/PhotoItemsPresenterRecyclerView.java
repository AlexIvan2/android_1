package alex.com.android_1.presenters.recyclerViewPresenters;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;


public class PhotoItemsPresenterRecyclerView implements PhotoItemsPresenter {

    List<PhotoItem> photoItems;
    Adapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void showPhotoItems(List<PhotoItem> photoItems, Activity activity, PhotoItemsPresenterCallback callback) {
        this.photoItems = photoItems;
        this.mRecyclerView = new RecyclerView(activity);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        this.mAdapter = new Adapter(photoItems, callback);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = ((GridLayoutManager)mLayoutManager).findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    callback.onLastItemReach(totalItemCount);
                }
            }
        });

        activity.setContentView(mRecyclerView);
    }

    @Override
    public void updateWithItems(List<PhotoItem> photoItems) {
        this.mAdapter.photoItems = photoItems;
        this.mAdapter.notifyDataSetChanged();
    }

}