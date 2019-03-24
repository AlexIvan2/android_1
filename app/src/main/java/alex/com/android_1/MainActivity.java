package alex.com.android_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int COLOR_CONST = 256;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeBackgroundColor();
    }

    private void changeBackgroundColor() {
        RelativeLayout relativeLayout = findViewById(R.id.main_layout);
        if (relativeLayout == null) {
            return;
        }
        relativeLayout.setBackgroundColor(Color.rgb(getNewColor(),
                getNewColor(),
                getNewColor()));
    }

    private int getNewColor() {
        Random rnd = new Random();
        return rnd.nextInt(COLOR_CONST);
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

        Intent intent = new Intent(MainActivity.this, LoginActivity2.class);
        startActivity(intent);

    }
}
