package se.mah.ac9511.da401a_david_ruland;


import android.bluetooth.le.AdvertiseData;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class quoteFragment extends Fragment {
View v;
TextView date;
    private String []quotes;
    private String display;
    TextView  text;
    Random r=new Random();
    public quoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_quote, container, false);
        date=(TextView)v.findViewById(R.id.txDate);
        final Calendar calendar=Calendar.getInstance();
        int dd=calendar.get(Calendar.DAY_OF_MONTH);
        int mm=calendar.get(Calendar.MONTH);
        int yy=calendar.get(Calendar.YEAR);
        date.setText(new StringBuilder().append(yy).append(" ").append(" - ").append(mm+1).append(" - ").append(dd));
        text = (TextView) v.findViewById(R.id.displayQuote);
        quotes=new String[3];
        quotes[0]="A bird in the hand is better than a nail in the foot.";
        quotes[1]="When I was born, I was so surprised I didn't talk for a year and a half.";
        quotes[2]="I'm not afraid to die.I just don't want to be there when it happens.";
        int random = r.nextInt(quotes.length);
        display=quotes[random];
        text.setText(display);
        //Log.i("Quote", display);
        return v;
    }


}
