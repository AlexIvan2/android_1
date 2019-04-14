package alex.com.android_1.catalog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alex.com.android_1.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogViewHolder {

    public @BindView(R.id.photoImage) ImageView itemViewImage;
    public @BindView(R.id.photoName) TextView itemViewName;
    public @BindView(R.id.photoLocation) TextView itemViewLocation;

    public CatalogViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}