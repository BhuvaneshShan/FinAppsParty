package com.thomas.bhuva.finappsparty;


import android.content.Intent;
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
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OneFragment extends Fragment {

    ListView cardListView;
    RegDbConn regdbconn;
    SQLiteDatabase db;
    //String[] transDetails = new String[]{"WALMART : 2345",
    //         "Target : 3343"};
    List<String> transDet = new ArrayList<String>();
    String[] CardDetails = new String[]{"Visa : 4042 5343 3434 3434",
            "PayPal : 3343 7565 2363 9678",
            "Master : 7896 8797 7897 7897"
            };
    public OneFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_one, container, false);

        cardListView = (ListView) view.findViewById(R.id.cardsListView);

        //Declaring Array adapter
        String dbquery = "select _id, card_number from register";
        try {
            regdbconn = new RegDbConn(getActivity().getApplicationContext());
            db = regdbconn.getReadableDatabase();
            Cursor c = db.rawQuery(dbquery, null);


            Log.e("QUERY", dbquery);
            int Column1 = c.getColumnIndex("card_number");
            //int Column2 = c.getColumnIndex("amount");
            int cnt = c.getCount();
            Log.e("count", Integer.toString(cnt));
            String Data = "";

            // Check if our result was valid.
            c.moveToFirst();


            // Loop through all Results
            do {
                String Name = c.getString(Column1);
                Random rn = new Random();
                int answer = rn.nextInt(4) + 1;
                String type;
                if (answer == 1)
                    type = "VISA";
                else if (answer == 2)
                    type = "MASTERCARD";
                else
                    type = "PAYPAL";
                transDet.add(0, Name + " : " + type);
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
            cardListView.setAdapter(countryAdapter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            final Button button = (Button) view.findViewById(R.id.addCard);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click

                    Intent activityChangeIntent = new Intent(getActivity().getApplicationContext(), RegInsert.class);

                    // currentContext.startActivity(activityChangeIntent);

                    startActivity(activityChangeIntent);
                }
            });

            return view;
    }

}
