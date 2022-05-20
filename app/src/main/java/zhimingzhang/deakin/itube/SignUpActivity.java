package zhimingzhang.deakin.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    ArrayList<User> UserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signupButton = findViewById(R.id.signupButton);
        EditText usernameEditText = findViewById(R.id.usernameSignup);
        EditText fullnameEditText = findViewById(R.id.usernameSignup);
        EditText passwordEditText = findViewById(R.id.passwordSignup);
        EditText confirmEditText = findViewById(R.id.confirmPasswordSignup);

        DatabaseHelper db = new DatabaseHelper(this);

        ArrayList<String> playlist = new ArrayList();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = fullnameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String cpassword = confirmEditText.getText().toString();

                if(password.equals(cpassword))
                {
                    User user = new User(username, password, playlist);
                    UserList.add(user);
                    long result = db.insertUser(user);
                    if(result > 0)
                    {
                        Toast.makeText(getApplicationContext(), "Signup Successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "passwords don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}