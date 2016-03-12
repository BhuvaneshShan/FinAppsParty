package com.thomas.bhuva.finappsparty;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class OneFragment extends Fragment {

    ListView cardListView;

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
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.select_dialog_item, CardDetails);

        //Setting the android ListView's adapter to the newly created adapter
        cardListView.setAdapter(countryAdapter);
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
        return view;
    }

}
