package alex.com.android_1.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    public interface ShareFragmentListener {
        void onInfoPress();
        void onSharePress();
    }

    public PhotoItem photoItem;
    private ShareFragmentListener listener;

    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.shareButton) Button shareButton;
    @BindView(R.id.infoButton) Button infoButton;

    public ShareFragment() {
        // Required empty public constructor
    }

    // Method that would connect callbacks of
    // Fragment to activity, no need to do that
    // Inside activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShareFragmentListener) {
            listener = (ShareFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ShareFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this, view);

        if (photoItem != null) {
            Glide.with(this).load(photoItem.getImgUrl()).into(imageView);
        }

        // Set buttons
        shareButton.setOnClickListener(button -> listener.onSharePress());

        infoButton.setOnClickListener(button -> listener.onInfoPress());

        return view;
    }

}
