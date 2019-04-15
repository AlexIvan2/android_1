package alex.com.android_1.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    public interface ShareFragmentCallback {
        void onInfoPress();
    }

    public PhotoItem photoItem;
    public ShareFragmentCallback callback;


    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        if (photoItem != null) {
            Glide.with(this).load(photoItem.getImgUrl()).into(imageView);
        }

        view.findViewById(R.id.infoButton).setOnClickListener(view1 -> callback.onInfoPress());

        return view;
    }

}
