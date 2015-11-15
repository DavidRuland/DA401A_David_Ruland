package se.mah.ac9511.assignment2;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class detailScreen extends Fragment {
    ArrayList<Movie> itemsArrayList=new ArrayList<Movie>();
    //ListView listView;
    TextView text1,text2,text3;
    ImageView image;
    String []title;
    String [] year;
    String []info;
    Resources res;
    TypedArray c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    TypedArray info1,info2,info3,info4,info5,info6,info7,info8,info9,info10;
    String cops,eat,force,four,haxan,let,myLife,persona,showMeLove,youTheLiving;
    int []img=new int[]{R.drawable.cops_fanart,
            R.drawable.eat_sleep_die_fanart,
            R.drawable.force_majeure_fanart,
            R.drawable.four_shades_of_brown_fanart,
            R.drawable.haxan_fanart,
            R.drawable.let_the_right_one_in_fanart,
            R.drawable.my_life_as_a_dog_fanart,
            R.drawable.persona_fanart,
            R.drawable.show_me_love_fanart,
            R.drawable.you_the_living_fanart};


    public detailScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_detail_screen, container, false);
        text1=(TextView)v.findViewById(R.id.T);
        text2=(TextView)v.findViewById(R.id.Y);
        text3=(TextView)v.findViewById(R.id.Information);
        image=(ImageView)v.findViewById(R.id.fanart);
        res=getResources();
        title=new String[10];
        year=new String[10];
        info=new String [10];
        Cops();
        Eat();
        force_majeure();
        four_shades_of_brown();
        haxan();
        let_the_right_one_in();
        my_life_as_a_dog();
        persona();
        show_me_love();
        //you_the_living(); Image to big to load

           return v;
       }

    public void Cops ()
    {
        cops=getResources().getStringArray(R.array.cops)[0];
        title[0]=cops;
        c1=res.obtainTypedArray(R.array.cops);
        year[0] =c1.getString(1);

        info1=res.obtainTypedArray(R.array.cops);
        info[0]=info1.getString(2);
        text1.setText(cops);
        text2.setText(year[0]);
        text3.setText(info[0]);
        image.setImageResource(img[0]);


    }
    public void Eat()
    {
        eat=getResources().getStringArray(R.array.eat_sleep_die)[0];
        title[1]=eat;
        c2=res.obtainTypedArray(R.array.eat_sleep_die);
        year[1]=c2.getString(1);

        info2=res.obtainTypedArray(R.array.eat_sleep_die);
        info[1]=info2.getString(2);
        text1.setText(eat);
        text2.setText(year[1]);
        text3.setText(info[1]);
        image.setImageResource(img[1]);

    }
    public void force_majeure()
    {
        force=getResources().getStringArray(R.array.force_majeure)[0];
        title[2]=force;
        c3= res.obtainTypedArray(R.array.force_majeure);
        year[2]=c3.getString(1);
        info3=res.obtainTypedArray(R.array.force_majeure);
        info[2]=info3.getString(2);


        info[2]=info3.getString(2);
        text1.setText(force);
        text2.setText(year[2]);
        text3.setText(info[2]);
        image.setImageResource(img[2]);



    }
    public void four_shades_of_brown()
    {
        four=getResources().getStringArray(R.array.four_shades_of_brown)[0];
        title[3]=four;
        c4=res.obtainTypedArray(R.array.four_shades_of_brown);
        year[3]=c4.getString(1);
        info4=res.obtainTypedArray(R.array.four_shades_of_brown);
        info[3]=info4.getString(2);
        text1.setText(four);
        text2.setText(year[3]);
        text3.setText(info[3]);
        image.setImageResource(img[3]);


    }
    public void haxan ()
    {
        haxan=getResources().getStringArray(R.array.haxan)[0];
        title[4]=haxan;
        c4=res.obtainTypedArray(R.array.haxan);
        year[4]=c4.getString(1);
        info5=res.obtainTypedArray(R.array.haxan);
        info[4]=info5.getString(2);
        text1.setText(haxan);
        text2.setText(year[4]);
        text3.setText(info[4]);
        image.setImageResource(img[4]);



    }
    public void let_the_right_one_in()
    {
        let=getResources().getStringArray(R.array.let_the_right_one_in)[0];
        title[5]=let;
        c5=res.obtainTypedArray(R.array.let_the_right_one_in);
        year[5]=c5.getString(1);
        info6=res.obtainTypedArray(R.array.let_the_right_one_in);
        info[5]=info6.getString(2);
        text1.setText(let);
        text2.setText(year[5]);
        text3.setText(info[5]);
        image.setImageResource(img[5]);



    }
    public void my_life_as_a_dog ()
    {
        myLife=getResources().getStringArray(R.array.my_life_as_a_dog)[0];
        title[6]=myLife;
        c6=res.obtainTypedArray(R.array.my_life_as_a_dog);
        year[6]=c6.getString(1);
        info7=res.obtainTypedArray(R.array.my_life_as_a_dog);
        info[6]=info7.getString(2);
        text1.setText(myLife);
        text2.setText(year[6]);
        text3.setText(info[6]);
        image.setImageResource(img[6]);

    }
    public void persona ()
    {
        persona=getResources().getStringArray(R.array.persona)[0];
        title[7]=persona;
        c7=res.obtainTypedArray(R.array.persona);
        year[7]=c7.getString(1);
        info8=res.obtainTypedArray(R.array.persona);
        info[7]=info8.getString(2);
        text1.setText(persona);
        text2.setText(year[7]);
        text3.setText(info[7]);
        image.setImageResource(img[7]);
    }
    public void show_me_love  ()
    {
        showMeLove=getResources().getStringArray(R.array.show_me_love)[0];
        title[8]=showMeLove;
        c8=res.obtainTypedArray(R.array.show_me_love);
        year[8]=c8.getString(1);
        info9=res.obtainTypedArray(R.array.show_me_love);
        info[8]=info9.getString(2);
        text1.setText(showMeLove);
        text2.setText(year[8]);
        text3.setText(info[8]);
        image.setImageResource(img[8]);
    }
    public void you_the_living()
    {

        youTheLiving=getResources().getStringArray(R.array.you_the_living)[0];
        title[9]=youTheLiving;
        c9=res.obtainTypedArray(R.array.you_the_living);
        year[9]=c3.getString(1);
        info10=res.obtainTypedArray(R.array.you_the_living);
        info[9]=info10.getString(2);
        text1.setText(youTheLiving);
        text2.setText(year[9]);
        text3.setText(info[9]);
        image.setImageResource(img[9]);

    }




}
