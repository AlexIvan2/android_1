package alex.com.android_1.catalog;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import alex.com.android_1.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogViewHolder {

    @BindView(R.id.photoImage) ImageView itemViewImage;
    @BindView(R.id.photoName) TextView itemViewName;
    @BindView(R.id.photoLocation) TextView itemViewLocation;
    @BindView(R.id.buttonFavorite) ImageButton buttonFavorite;

    public CatalogViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
