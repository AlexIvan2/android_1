package alex.com.android_1.dataSources.giphy;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alex.com.android_1.interfaces.NetworkingResultListener;
import alex.com.android_1.interfaces.NetworkingManager;
import alex.com.android_1.interfaces.PhotoItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GiphyNetworkingManager implements NetworkingManager {


    private List<PhotoItem> photoItems = new ArrayList<>();
    private int limit = 50;
    private int offset = 0;
    private boolean requestInProgress = false;

    @Override
    public void getPhotoItems(NetworkingResultListener result) {
        getImages(result);
    }

    private void getImages(NetworkingResultListener listener) {

        Request request = new Request.Builder()
                .url("https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit=" + limit + "&offset=" + offset + "&rating=G")
                .build();

        OkHttpClient client = new OkHttpClient();
        requestInProgress = true;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final Gson gson = new Gson();
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
        if (requestInProgress) {
            return;
        }
        if (offset <= lastPosition) {
            requestInProgress = true;
            offset += limit;
            getPhotoItems(result);
        }
    }

//    @Override
//    public void onResponse(Call call, Response response) throws IOException {
//        final Gson gson = new Gson();
//        String jsonString = response.body() != null ? response.body().string() : null;
//        try {
//
//            JsonElement jelement = new JsonParser().parse(jsonString);
//            JsonObject jsonData = jelement.getAsJsonObject();
//            JsonArray jsonArray = jsonData.getAsJsonArray("data");
//
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JsonElement imgObject = jsonArray.get(i);
//                PhotoItemGiphy item = gson.fromJson(imgObject.toString(), PhotoItemGiphy.class);
//                photoItems.add(item);
//            }
//
//            if (this.resultCallback != null) {
//                this.resultCallback.onPhotoItemsCompleteCallback(photoItems);
//            }
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        }
//    }

}
