package sg.edu.rp.c346.id20020036.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etName, etMobile, etHome, etEmail, etAddress, etInfo;
    Button btnInsert, btnShowList;
    ToggleButton toggleButton;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etHome = findViewById(R.id.etHome);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etInfo = findViewById(R.id.etInfo);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnList);
        toggleButton = findViewById(R.id.toggleButton);
        rg = findViewById(R.id.rg);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String home = etHome.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String info = etInfo.getText().toString().trim();

                if(name.length() == 0 || mobile.length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Incomplete Data", Toast.LENGTH_LONG).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                String gender = String.valueOf(getGender());
                dbh.insertContact(name, mobile, home, email, address, gender, info);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etName.setText("");
                etMobile.setText("");
                etHome.setText("");
                etEmail.setText("");
                etAddress.setText("");
                etInfo.setText("");
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });


    }

    private String getGender()
    {
        String gender = "";
        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.rgFemale:
                gender = "Female";
                break;
            case R.id.rgMale:
                gender = "Male";
                break;
        }
        return gender;
    }
}