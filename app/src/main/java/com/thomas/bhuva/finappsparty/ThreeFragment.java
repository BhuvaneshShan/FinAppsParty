package com.thomas.bhuva.finappsparty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {


    public ThreeFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_three, container, false);
        RadioButton gps = (RadioButton) view.findViewById(R.id.GPS);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transaction.mode = Transaction.GPS;

            }
        });
        RadioButton vp = (RadioButton) view.findViewById(R.id.VolumeKeyPress);
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transaction.mode = Transaction.VOLUMEPRESS;

            }
        });
                return view;
    }
    /*
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.GPS:
                if (checked)
                    // Pirates are the best
                    Transaction.mode = Transaction.GPS;
                    break;
            case R.id.VolumeKeyPress:
                if (checked)
                    Transaction.mode = Transaction.VOLUMEPRESS;
                    // Ninjas rule
                    break;
        }
    }
    */
}
