package alex.com.android_1.http;

import java.util.List;

import alex.com.android_1.PhotoItem;

public class CatalogService {

    private BaseCallBack listener;

    public void registerCallBackListener(BaseCallBack listener) {
        this.listener = listener;
    }

    public List<PhotoItem> getPhotoItems() {
        return listener.doJob();
    }
}
