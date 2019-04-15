package alex.com.android_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import alex.com.android_1.interfaces.PhotoItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textViewAuthor)
    TextView textViewAuthor;

    private static String TEXT_VIEW_DATA_KEY = "TEXT_VIEW_DATA_KEY";
    private static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";
    private PhotoItem photoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);

        photoItem = (PhotoItem) getIntent().getSerializableExtra(PHOTO_ITEM_KEY);
        if (photoItem != null) {
            textViewAuthor.setText(photoItem.getUserName());
            Glide.with(this).load(photoItem.getImgUrl()).into(imageView);
        }
    }

    public static Intent buildIntent(Context context, PhotoItem photoItem) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra(ShareActivity.PHOTO_ITEM_KEY, photoItem);
        return intent;
    }

//    public void onSetBtnClick(View view) {
//        textView.setText(editText.getText());
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putCharSequence(TEXT_VIEW_DATA_KEY, textView.getText());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        textView.setText(savedInstanceState.getCharSequence(TEXT_VIEW_DATA_KEY));
    }
}
