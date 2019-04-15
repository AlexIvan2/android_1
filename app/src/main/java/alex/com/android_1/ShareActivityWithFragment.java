package alex.com.android_1;

import android.app.FragmentTransaction;
import android.os.Bundle;

import alex.com.android_1.fragments.InfoFragment;
import alex.com.android_1.fragments.ShareFragment;

public class ShareActivityWithFragment extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_fragment);

        showShareFragment();
    }

    private void showShareFragment() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ShareFragment fragment = new ShareFragment();
        fragment.photoItem = this.photoItem;
        fragment.callback = () -> showInfoFragment();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();

    }
    private void showInfoFragment() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        InfoFragment fragment = new InfoFragment();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();

    }
}
