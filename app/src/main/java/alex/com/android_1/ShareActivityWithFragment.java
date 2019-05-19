package alex.com.android_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import alex.com.android_1.fragments.InfoFragment;
import alex.com.android_1.fragments.ShareFragment;

public class ShareActivityWithFragment extends BaseActivity implements ShareFragment.ShareFragmentListener, InfoFragment.InfoFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_fragment);

        showShareFragment();
    }

    private void showShareFragment() {

        ShareFragment fragment = new ShareFragment();
        fragment.photoItem = this.photoItem;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    private void showInfoFragment() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        InfoFragment fragment = new InfoFragment();
        fragment.photoItem = this.photoItem;
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();

    }

    @Override
    public void onInfoPress() {
        showInfoFragment();
    }

    @Override
    public void onClosePress() {
        showShareFragment();
    }

    @Override
    public void onSharePress() {
        // Create intent with action
        Intent i = new Intent(Intent.ACTION_SEND);
        // Set additions
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        // Start intent
        startActivity(Intent.createChooser(i, "Share URL"));
    }


}
