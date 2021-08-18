package sg.edu.rp.c346.id20020036.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Contacts> contactsList;
    CustomAdapter aa;
    Button btnShowFavs, btnShowAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.lv);
        btnShowFavs = findViewById(R.id.btnShowFavs);
        btnShowAll = findViewById(R.id.btnShowAll);

        DBHelper dbh = new DBHelper(this);
        contactsList = dbh.getAllContacts();
        dbh.close();

        Intent i = getIntent();

        aa = new CustomAdapter(this, R.layout.list, contactsList);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListActivity.this, EditListActivity.class);
                i.putExtra("contact", contactsList.get(position));
                startActivity(i);
            }
        });

        btnShowFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                contactsList.clear();
                contactsList.addAll(dbh.getAllContactsByFav("true"));
                aa.notifyDataSetChanged();
            }
        });

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                contactsList.clear();
                contactsList.addAll(dbh.getAllContacts());
                aa.notifyDataSetChanged();
            }
        });
    }
}