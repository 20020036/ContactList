package sg.edu.rp.c346.id20020036.contactlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditListActivity extends AppCompatActivity {

    TextView tvID;
    EditText etName, etMobile, etHome, etEmail, etAddress, etInfo;
    RadioGroup rg;
    RadioButton rgFemale, rgMale;
    Button btnCancel, btnUpdate, btnDelete;
    CheckBox cbFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        tvID = findViewById(R.id.tvID);
        etName = findViewById(R.id.etName3);
        etMobile = findViewById(R.id.etMobile3);
        etHome = findViewById(R.id.etHome3);
        etEmail = findViewById(R.id.etEmail3);
        etAddress = findViewById(R.id.etAddress3);
        etInfo = findViewById(R.id.etInfo3);
        rg = findViewById(R.id.rgGender);
        rgFemale = findViewById(R.id.rgFemale3);
        rgMale = findViewById(R.id.rgMale3);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        cbFav = findViewById(R.id.cbFav3);

        Intent i = getIntent();
        final Contacts currentContact = (Contacts) i.getSerializableExtra("contact");

        tvID.setId(currentContact.getId());
        etName.setText(currentContact.getName());
        etMobile.setText(currentContact.getMobile());
        etHome.setText((currentContact.getHome()));
        etEmail.setText(currentContact.getEmail());
        etAddress.setText(currentContact.getAddress());
        if(currentContact.getGender() == "Female")
        {
            rgFemale.setChecked(true);
            rgMale.setChecked(false);
        }
        else
        {
            rgFemale.setChecked(false);
            rgMale.setChecked(true);
        }
        etInfo.setText(currentContact.getInfo());
        if(currentContact.getFav() == "true")
        {
            cbFav.setChecked(true);
        }
        else
        {
            cbFav.setChecked(false);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditListActivity.this);
                currentContact.setName(etName.getText().toString().trim());
                currentContact.setMobile(etMobile.getText().toString().trim());
                currentContact.setHome(etHome.getText().toString().trim());
                currentContact.setEmail(etEmail.getText().toString().trim());
                currentContact.setAddress(etAddress.getText().toString().trim());
                currentContact.setGender(getTheGender());
                currentContact.setInfo(etInfo.getText().toString().trim());
                currentContact.setFav(getIfFav());

                int result = dbh.updateContact(currentContact);
                if(result > 0)
                {
                    Toast.makeText(EditListActivity.this, "Contact Updated", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(EditListActivity.this, "Update failed.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditListActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the contact\n" + currentContact.getName() + "?");
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditListActivity.this);
                        int result = dbh.deleteContact(currentContact.getId());
                        if(result > 0)
                        {
                            Toast.makeText(EditListActivity.this, "Contact deleted", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(EditListActivity.this, "Delete failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                myBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditListActivity.this, "Delete failed", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditListActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to dicard the changes?");
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { finish(); }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
    private String getIfFav()
    {
        String fav = "";
        if(cbFav.isChecked() == true)
        {
            fav = "true";
        }
        else
        {
            fav = "false";
        }
        return fav;
    }

    private String getTheGender()
    {
        String gender = "";
        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.rgFemale3:
                gender = "🙎🏻‍♀️";
                break;
            case R.id.rgMale3:
                gender = "🙎🏻‍♂️";
                break;
        }
        return gender;
    }
}