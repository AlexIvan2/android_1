package alex.com.android_1.dataSources.giphy;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alex.com.android_1.interfaces.NetworkResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GiphyNetworkingManager implements NetworkingManager, Callback {

    private static final String GIPHY_URL = "https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit=25&rating=G";

    private List<PhotoItem> photoItems = new ArrayList<>();

    NetworkResultListener resultCallback;

    public GiphyNetworkingManager(NetworkResultListener resultCallback) {
        this.resultCallback = resultCallback;
    }

    @Override
    public void getImages() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(GIPHY_URL)
                .build();
        client.newCall(request).enqueue(this);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final Gson gson = new Gson();
        String jsonString = response.body() != null ? response.body().string() : null;
        try {

            JsonElement jelement = new JsonParser().parse(jsonString);
            JsonObject jsonData = jelement.getAsJsonObject();
            JsonArray jsonArray = jsonData.getAsJsonArray("data");

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement imgObject = jsonArray.get(i);
                PhotoItemGiphy item = gson.fromJson(imgObject.toString(), PhotoItemGiphy.class);
                photoItems.add(item);
            }

            if (this.resultCallback != null) {
                this.resultCallback.onPhotoItemsCompleteCallback(photoItems);
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }

}
