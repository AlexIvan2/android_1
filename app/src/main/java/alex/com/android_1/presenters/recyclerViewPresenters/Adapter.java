package alex.com.android_1.presenters.recyclerViewPresenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import alex.com.android_1.interfaces.PhotoItemsPresenterCallback;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderRecyclerView> {

    List<PhotoItem> photoItems;
    PhotoItemsPresenterCallback callback;


    public Adapter(List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback) {
        this.photoItems = photoItems;
        this.callback = callback;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolderRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new ViewHolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRecyclerView holder, int position) {

        PhotoItem photoItem = this.photoItems.get(position);

        //set favorite button behaviour
        updateFavoriteButton(holder.button, photoItem);

        //callback to favorite button
        holder.button.setOnClickListener(view -> {
            callback.onItemToggleFavorite(photoItem);
            updateFavoriteButton(holder.button, photoItem);
        });

        holder.imageViewPhoto.setOnClickListener(view -> callback.onItemSelected(photoItem));

        holder.textViewLocation.setText(photoItem.getLocation());
        holder.textViewAuthor.setText(photoItem.getUserName());
        Glide.with(holder.itemView).load(photoItem.getImgUrl()).into(holder.imageViewPhoto);

//        Picasso.get().load(photoItem.getImgUrl()).placeholder(R.drawable.placeholder).into(holder.imageViewPhoto);
    }


    private void updateFavoriteButton(ImageButton imageButton, PhotoItem photoItem) {
//        boolean isFavorited = SharedPreferencesHelper.isFavorited(photoItem.getID(), context);

        boolean isFavorited = photoItem.isSavedToDatabase();
        if (isFavorited) {
            imageButton.setImageResource(R.drawable.favorite_on);
        } else {
            imageButton.setImageResource(R.drawable.favorite_off);
        }
    }

    @Override
    public int getItemCount() {
        return this.photoItems.size();
    }

    public static class ViewHolderRecyclerView extends RecyclerView.ViewHolder {

        @BindView(R.id.photoImage)
        public ImageView imageViewPhoto;
        @BindView(R.id.photoName)
        public TextView textViewAuthor;
        @BindView(R.id.photoLocation)
        TextView textViewLocation;
        @BindView(R.id.buttonFavorite)
        public ImageButton button;

        ViewHolderRecyclerView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
