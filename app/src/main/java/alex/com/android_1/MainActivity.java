package alex.com.android_1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onButtonClick(View view) {
        TextView textViewMain = findViewById(R.id.main_text_view);
        if(textViewMain == null) {
            return;
        }

        textViewMain.setText("aaaa");

        RelativeLayout mainLayout = findViewById(R.id.main_layout);
        if(mainLayout == null) {
            return;
        }

        mainLayout.setBackgroundColor(Color.BLACK);

    }
}
