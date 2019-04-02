package alex.com.android_1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogViewHolder {

    @BindView(R.id.carImage) ImageView itemViewImage;
    @BindView(R.id.carName) TextView itemViewName;

    public CatalogViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
