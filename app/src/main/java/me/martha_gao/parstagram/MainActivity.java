package me.martha_gao.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signupButton;
    private ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.btLogin);
        signupButton = findViewById(R.id.btSignup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                login(username, password);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // first parameter is the context, second is the class of the activity to launch
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i); // brings up the second activity
            }
        });

        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    currentUser = ParseUser.getCurrentUser();
                    Log.d("LoginActivity", "Login successful");
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT);
                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("LoginActivity", "Login failed");
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}
