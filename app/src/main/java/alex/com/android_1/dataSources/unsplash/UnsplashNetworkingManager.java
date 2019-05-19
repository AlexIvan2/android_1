package alex.com.android_1.dataSources.unsplash;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alex.com.android_1.dataSources.giphy.PhotoItemGiphy;
import alex.com.android_1.interfaces.NetworkingResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UnsplashNetworkingManager implements NetworkingManager {

    private static final String GIPHY_URL = "https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542";

    private List<PhotoItem> photoItems = new ArrayList<>();
    private NetworkingResultListener resultListener;
    private boolean requestInProgress = false;


    @Override
    public void getPhotoItems(NetworkingResultListener result) {
        getImages(result);
    }

    private void getImages(NetworkingResultListener listener) {

        Request request = new Request.Builder()
                .url(GIPHY_URL)
                .build();

        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();

        requestInProgress = true;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String jsonString = response.body().string();

                    JsonElement jelement = new JsonParser().parse(jsonString);
                    JsonObject jsonData = jelement.getAsJsonObject();
                    JsonArray jsonArray = jsonData.getAsJsonArray("data");

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonElement imgObject = jsonArray.get(i);
                        PhotoItemGiphy item = gson.fromJson(imgObject.toString(), PhotoItemGiphy.class);
                        photoItems.add(item);
                    }

                } catch (Exception e) {
                    Log.e("ERROR", e.getLocalizedMessage());

                } finally {
                    requestInProgress = false;
                    listener.onPhotoItemsCompleteCallback(photoItems);
                }


            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result) {

    }
}
