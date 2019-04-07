package alex.com.android_1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogViewHolder {

    @BindView(R.id.photoImage) ImageView itemViewImage;
    @BindView(R.id.photoName) TextView itemViewName;
    @BindView(R.id.photoLocation) TextView itemViewLocation;

    public CatalogViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
