package zhimingzhang.deakin.itube;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText usernameEditText;
        EditText passwordEditText;
        Button signupButton;
        Button loginButton;
        DatabaseHelper db = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupButton = findViewById(R.id.signUpButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
        loginButton = findViewById(R.id.loginButton);
        usernameEditText = findViewById(R.id.usernameInput);
        passwordEditText = findViewById(R.id.passwordInput);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean res = db.fetchUser(username, password);
                int userID = db.getUserID(username);
                if(res) {
                    Toast.makeText(MainActivity.this, "Succeeded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, PlayerInterface.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}