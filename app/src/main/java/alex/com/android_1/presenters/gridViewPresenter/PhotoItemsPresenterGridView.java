package alex.com.android_1.presenters.gridViewPresenter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenter;
import alex.com.android_1.catalog.ViewAdapter;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {

    List<PhotoItem> photoItems;
    ArrayAdapter<PhotoItem> dataAdapter;

    @Override
    public void setupWithPhotoItems(List<PhotoItem> photoItems, Activity activity, PhotoItemsPresenterCallback callback) {
        this.photoItems = photoItems;
        dataAdapter = new ArrayAdapter<PhotoItem>(activity, 0, this.photoItems) {
            @Override
            public View getView(int position,
                                View convertView,
                                ViewGroup parent) {
                PhotoItem photoItem = photoItems.get(position);
                // Inflate only once
                if (convertView == null) {
                    convertView = activity.getLayoutInflater()
                            .inflate(R.layout.photo_item, null, false);
                }

                ViewHolder viewHolder;
                if (convertView.getTag() == null) {
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.itemViewName.setText(photoItem.getUserName());

                viewHolder.buttonFavorite.setOnClickListener(view -> {
                    callback.onItemToggleFavorite(photoItem);
                    updateFavoriteButton(viewHolder.buttonFavorite, photoItem, activity);
                });

                Glide.with(convertView).load(photoItem.getImgUrl()).into(viewHolder.itemViewImage);
                return convertView;

            }

            @Override
            public int getCount() {
                return photoItems.size();
            }

        };

        GridView gridView = new GridView(activity);
        activity.setContentView(gridView);
//        ViewAdapter adapter = new ViewAdapter(activity, R.layout.photo_item, photoItems);
        gridView.setAdapter(dataAdapter);

        setOnTouchListener(activity, gridView);
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
        dataAdapter.notifyDataSetChanged();
    }

    private void updateFavoriteButton(ImageButton imageButton, PhotoItem photoItem, Context context) {
//        boolean isFavorited = SharedPreferencesHelper.isFavorited(photoItem.getID(), context);

        boolean isFavorited = photoItem.isSavedToDatabase();
        if (isFavorited) {
            imageButton.setImageResource(R.drawable.favorite_on);
        } else {
            imageButton.setImageResource(R.drawable.favorite_off);
        }
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
