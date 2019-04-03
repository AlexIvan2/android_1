package alex.com.android_1.http;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alex.com.android_1.PhotoItem;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Listener implements BaseCallBack {

    private List<PhotoItem> photoItems = new ArrayList<>();

    public List<PhotoItem> getPhotoItems() {
        return photoItems;
    }

    @Override
    public List<PhotoItem> doJob() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();
        client.newCall(request).enqueue(this);

        return photoItems;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final Gson gson = new Gson();
        String jsonData = response.body() != null ? response.body().string() : null;
        try {
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0; i < array.length(); i++) {
                JSONObject imgObject = array.getJSONObject(i);
                PhotoItem item = gson.fromJson(imgObject.toString(), PhotoItem.class);
                photoItems.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
