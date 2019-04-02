package alex.com.android_1;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import alex.com.android_1.http.BasicCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);



    }

    @NonNull
    private List<CarObject> getCarObjects() {

        OkHttpClient client = new OkHttpClient();

        final List<PhotoItem> photoItems = new ArrayList<>();

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Gson gson = new Gson();
                String jsonData = response.body() != null ? response.body().string() : null;
                try {
                    JSONArray array = new JSONArray(jsonData);
                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject imgObject = array.getJSONObject(i);
                        PhotoItem item = gson.fromJson(imgObject.toString(), PhotoItem.class);
                        photoItems.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(() -> showPhotoItems(photoItems));
            }


            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

        Resources res = getResources();
        final List<String> carsNameArray = Arrays.asList(res.getStringArray(R.array.car_types));
        return carsNameArray.stream().map(carName ->
                new CarObject(getResources().getDrawable(this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName()), null), carName))
                .collect(Collectors.toList());
    }

    private void setOnTouchListener(GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            Resources res = getResources();
            String[] items = res.getStringArray(R.array.car_types);

            Toast toast = Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    private void showPhotoItems(List<PhotoItem> items) {

        GridView resultView = findViewById(R.id.catalogView);

        List<CarObject> carObjects = getCarObjects();
        List<PhotoItem> carObjects = getCarObjects();

        GridViewAdapter adapter = new GridViewAdapter(this, R.layout.car_item, items);

        resultView.setAdapter(adapter);
        setOnTouchListener(resultView);

    }


    @NonNull
    private List<PhotoItem> getPhotoItems() {

        OkHttpClient client = new OkHttpClient();

        final List<PhotoItem> photoItems = new ArrayList<>();

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Gson gson = new Gson();
                String jsonData = response.body() != null ? response.body().string() : null;
                try {
                    JSONArray array = new JSONArray(jsonData);
                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject imgObject = array.getJSONObject(i);
                        PhotoItem item = gson.fromJson(imgObject.toString(), PhotoItem.class);
                        photoItems.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(() -> showPhotoItems(photoItems));
            }


            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

        Resources res = getResources();
        final List<String> carsNameArray = Arrays.asList(res.getStringArray(R.array.car_types));
        return carsNameArray.stream().map(carName ->
                new CarObject(getResources().getDrawable(this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName()), null), carName))
                .collect(Collectors.toList());
    }
}
