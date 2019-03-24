package alex.com.android_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        View view = findViewById(R.id.registration_header);
        ImageButton headerBackBtn = view.findViewById(R.id.header_back);
        ImageButton headerCloseBtn = view.findViewById(R.id.header_close);

        headerBackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        headerCloseBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void onSubmitClick(View view) {
        //TODO: Validation and stuff
    }
}
