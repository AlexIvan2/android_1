package alex.com.android_1.http;

import android.content.SyncResult;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import alex.com.android_1.PhotoItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BasicCallBack implements Callback {

    final SyncResult syncResult = new SyncResult();



    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final Gson gson = new Gson();
        String jsonData = response.body() != null ? response.body().string() : null;
        try {
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0 ; i < array.length() ; i++) {
                JSONObject imgObject = array.getJSONObject(i);
                PhotoItem item = gson.fromJson(imgObject.toString(), PhotoItem.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
    }


}
