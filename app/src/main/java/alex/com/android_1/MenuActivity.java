package alex.com.android_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import alex.com.android_1.catalog.CatalogGridViewActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onPhotosClick(View view) {
        Intent intent = new Intent(MenuActivity.this, CatalogGridViewActivity.class);
        startActivity(intent);
    }


//    public void onGifsClick(View view) {
//        Intent intent = new Intent(MenuActivity.this, CatalogListViewActivity.class);
//        startActivity(intent);
//    }
}
