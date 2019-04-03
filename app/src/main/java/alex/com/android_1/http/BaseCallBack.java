package alex.com.android_1.http;

import java.util.List;

import alex.com.android_1.PhotoItem;
import okhttp3.Callback;

public interface BaseCallBack extends Callback {

    List<PhotoItem> doJob();
}
