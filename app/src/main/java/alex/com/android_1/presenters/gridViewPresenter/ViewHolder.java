package alex.com.android_1.presenters.gridViewPresenter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import alex.com.android_1.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder {

    public @BindView(R.id.photoImage) ImageView itemViewImage;
    public @BindView(R.id.photoName) TextView itemViewName;
    public @BindView(R.id.photoLocation) TextView itemViewLocation;
    public @BindView(R.id.buttonFavorite) ImageButton buttonFavorite;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
