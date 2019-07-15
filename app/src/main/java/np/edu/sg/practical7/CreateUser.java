package np.edu.sg.practical7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateUser.this,MainActivity.class);
                startActivity(i);
            }
        });

        Button create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userTxt = findViewById(R.id.newUserName);
                String newUserName = userTxt.getText().toString();
                EditText passTxt = findViewById(R.id.newPassword);
                String newPassword = passTxt.getText().toString();

                Pattern userp = Pattern.compile("^(?=[a-zA-Z0-9]).{6,12}$");
                Matcher userm = userp.matcher(newUserName);
                Pattern passp = Pattern.compile("^(?=.*[!@#])(?=.*[0-9])(?=.*[A-Z]).*$");
                Matcher passm = passp.matcher(newPassword);

                if(userm.matches()&&passm.matches()){
                    DbHandler db = new DbHandler(CreateUser.this, null, null, 1);
                    UserData newuser = new UserData();
                    newuser.setMyUserName(newUserName);
                    newuser.setMyPassword(newPassword);
                    db.addUser(newuser);

                    Toast.makeText(CreateUser.this, "New User Created Successfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CreateUser.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(CreateUser.this, "Invalid User Creation. Please Try Again.", Toast.LENGTH_SHORT).show();
                }
                /*
                SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE).edit();
                editor.putString("User", newUserName);
                editor.putString("Password", newPassword);
                editor.apply();
                */
            }

        });
    }
}
