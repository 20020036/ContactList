package sg.edu.rp.c346.id20020036.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Contacts> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Contacts> objects)
    {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(layout_id, parent, false);

        TextView tvName = customView.findViewById(R.id.tvName1);
        TextView tvMobile = customView.findViewById(R.id.tvMobile1);
        TextView tvHome = customView.findViewById(R.id.tvHome1);
        TextView tvEmail = customView.findViewById(R.id.tvEmail1);
        TextView tvAddress = customView.findViewById(R.id.tvAddress1);
        TextView tvGender = customView.findViewById(R.id.tvGender1);
        TextView tvInfo = customView.findViewById(R.id.tvInfo1);
        TextView tvFav = customView.findViewById(R.id.tvFav1);

        Contacts currentVersion = versionList.get(position);

        tvName.setText(currentVersion.getName());
        tvMobile.setText(currentVersion.getMobile());
        tvHome.setText(currentVersion.getHome());
        tvEmail.setText(currentVersion.getEmail());
        tvAddress.setText(currentVersion.getAddress());
        if(currentVersion.getGender() == "🙎🏻‍♂️")
        {
            tvGender.setText("👨🏻");
        }
        else if(currentVersion.getGender() == "🙎🏻‍♀️")
        {
            tvGender.setText("👩🏻");
        }
        else
        {
            tvGender.setText("🧑");
        }
        tvInfo.setText(currentVersion.getInfo());
        if(currentVersion.getFav() == "true")
        {
            tvFav.setText("⭐");
        }
        else
        {
            tvFav.setText("");
        }

        return customView;

    }


}
