package alex.com.android_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import alex.com.android_1.catalog.CatalogGridViewActivity;

public class LoginActivity extends AppCompatActivity {

    private static String KEY_EMEAIL = "KEY_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void onLoginPress(View view) {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        //#############SHARED PREF##############
//        SharedPreferences preferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);
//        String email = preferences.getString(KEY_EMEAIL, "");
//
//        if (email.isEmpty()) {
//
//        } else {
//            email = editTextEmail.getText().toString();
//
//        }
//
//
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(KEY_EMEAIL, email);
//        editor.apply();

        if (StringUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, getString(R.string.empty_email_error), Toast.LENGTH_LONG).show();
        } else if (StringUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, getString(R.string.empty_password_error), Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, CatalogGridViewActivity.class);
            startActivity(intent);
        }

    }
}
