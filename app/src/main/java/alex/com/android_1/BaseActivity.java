package alex.com.android_1;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import alex.com.android_1.interfaces.PhotoItem;

@SuppressLint("Registered")
public class BaseActivity extends Activity {

    private static String TEXT_VIEW_DATA_KEY = "TEXT_VIEW_DATA_KEY";
    private static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";
    protected PhotoItem photoItem;

    public static Intent buildIntent(Context context, PhotoItem photoItem) {
        Intent intent = new Intent(context, ShareActivityWithFragment.class);
        intent.putExtra(PHOTO_ITEM_KEY, photoItem);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoItem = (PhotoItem) getIntent().getSerializableExtra(PHOTO_ITEM_KEY);
    }
}
