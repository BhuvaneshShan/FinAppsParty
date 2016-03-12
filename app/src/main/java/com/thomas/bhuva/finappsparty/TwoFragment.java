package com.thomas.bhuva.finappsparty;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class TwoFragment extends Fragment {

    ListView transListView;
    RegDbConn regdbconn;
    SQLiteDatabase db;
    //String[] transDetails = new String[]{"WALMART : 2345",
   //         "Target : 3343"};
    List<String> transDet = new ArrayList<String>();

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_two, container, false);

        transListView = (ListView) view.findViewById(R.id.transactionListView);

        String dbquery = "select _id, vendor_name,amount from transaction1";
        try {
            regdbconn = new RegDbConn(getActivity().getApplicationContext());
            db = regdbconn.getReadableDatabase();
            Cursor c = db.rawQuery(dbquery, null);


            Log.e("QUERY", dbquery);
            int Column1 = c.getColumnIndex("vendor_name");
            int Column2 = c.getColumnIndex("amount");
            int cnt = c.getCount();
            Log.e("count", Integer.toString(cnt));
            String Data = "";

            // Check if our result was valid.
            c.moveToFirst();


            // Loop through all Results
            do {
                String Name = c.getString(Column1);
                String Amount = c.getString(Column2);
                transDet.add(0, Name + " : " + Amount);
                /*
                 * The fish image is stored in the drawable resources with scname as the file name of respective fish
                 * imageResource stores the resource id of the corresponding fish image
                 */

            } while (c.moveToNext());
            Log.e("Data: ", Data);
            String[] td = new String[transDet.size()];
            td = transDet.toArray(td);


            //Declaring Array adapter
            ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.select_dialog_item, td);

            //Setting the android ListView's adapter to the newly created adapter
            transListView.setAdapter(countryAdapter);
            transListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    transListView.deferNotifyDataSetChanged();
                }
            });

        }
        catch(Exception e){
            e.printStackTrace();
        }
        transListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                /*
                int itemPosition = position;


                //Get the String value of the item where the user clicked
                String itemValue = (String) cardListView.getItemAtPosition(position);

                //In order to start displaying new activity we need an intent
                Intent intent = new Intent(getContext(), CountryActivity.class);

                //Putting the Id of image as an extra in intent
                intent.putExtra("flag", FlagId[position]);

                //Here we will pass the previously created intent as parameter
                startActivity(intent);
                */
            }
        });
        return view;
    }

}
