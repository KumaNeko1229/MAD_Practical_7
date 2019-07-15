package np.edu.sg.practical7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Newuser = findViewById(R.id.newUserTxt);
        Newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CreateUser.class);
                startActivity(i);
            }
        });

        Button Login = findViewById(R.id.logIn);
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText user = findViewById(R.id.userName);
                String username = user.getText().toString();
                EditText pass = findViewById(R.id.password);
                String password = pass.getText().toString();
                DbHandler DB = new DbHandler(MainActivity.this,null,null,1);

                Pattern userp = Pattern.compile("^(?=[a-zA-Z0-9]).{6,12}$");
                Matcher userm = userp.matcher(username);
                Pattern passp = Pattern.compile("^(?=.*[!@#])(?=.*[0-9])(?=.*[A-Z]).*$");
                Matcher passm = passp.matcher(password);
                if(userm.matches()==false||passm.matches()==false){
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG).show();
                }
                UserData userData = DB.findUser(username);
                Log.d("help","usep = "+userm.matches());
                Log.d("help","passp = "+passm.matches());
                Log.d("help", "Username: "+userData.getMyUserName());
                Log.d("help", "Password: "+userData.getMyPassword());

                if (userData.getMyPassword().equals(password)){
                    Toast.makeText(MainActivity.this, "Valid", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
