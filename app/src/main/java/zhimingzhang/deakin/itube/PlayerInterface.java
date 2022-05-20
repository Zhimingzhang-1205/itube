package zhimingzhang.deakin.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_interface);

        Intent intent = getIntent();
        Integer userID = intent.getIntExtra("userID", 0);


        EditText linkInputEditText = findViewById(R.id.linkInput);

        Button playButton = findViewById(R.id.playButton);
        Button addButton = findViewById(R.id.addButton);
        Button playlistButton = findViewById(R.id.showButton);

        DatabaseHelper db = new DatabaseHelper(this);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String videoLink = linkInputEditText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), VideoPlayerActivity.class);
                intent.putExtra("videoLink", videoLink);
                startActivity(intent);
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String link = linkInputEditText.getText().toString();
                db.insertVideo(userID, link);



            }
        });

        playlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PlaylistActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
    }
}