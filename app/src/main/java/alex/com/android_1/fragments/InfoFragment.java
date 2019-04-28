package alex.com.android_1.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import alex.com.android_1.R;
import alex.com.android_1.interfaces.PhotoItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    public interface InfoFragmentListener {
        void onClosePress();
    }

    public PhotoItem photoItem;
    private InfoFragmentListener listener;

    @BindView(R.id.textViewAuthor)
    TextView textViewAuthor;
    @BindView(R.id.infoCloseButton)
    Button closeButton;

    public InfoFragment() {
        // Required empty public constructor
    }

    // Method that would connect callbacks of
    // Fragment to activity, no need to do that
    // Inside activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoFragmentListener) {
            listener = (InfoFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement InfoFragmentListener");
        }
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        if (photoItem != null) {
            textViewAuthor.setText(photoItem.getUserName());
        }

        closeButton.setOnClickListener(button -> listener.onClosePress());

        return view;
    }


}
