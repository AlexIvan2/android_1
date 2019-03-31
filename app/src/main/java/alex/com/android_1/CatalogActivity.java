package alex.com.android_1;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        GridView resultView = findViewById(R.id.catalogView);

        List<CarObject> carObjects = getCarObjects();
        GridViewAdapter adapter = new GridViewAdapter(this, R.layout.car_item, carObjects);

        resultView.setAdapter(adapter);
        setOnTouchListener(resultView);
    }

    @NonNull
    private List<CarObject> getCarObjects() {
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
}
