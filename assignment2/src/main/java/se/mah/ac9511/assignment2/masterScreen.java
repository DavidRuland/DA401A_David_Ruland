package se.mah.ac9511.assignment2;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.renderscript.RenderScript;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.ListFragment;
import java.util.ArrayList;
import android.content.res.Resources;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class masterScreen extends Fragment {
    //Out of memory leaks
    ArrayList<Movie>itemsArrayList=new ArrayList<Movie>();
    ListView listView;
    detailScreen dS;
    Handler hand;
    String cops,eat,force,four,haxan,let,myLife,persona,showMeLove,youTheLiving;
    String []title;
    String [] year;
    int[]pos;
    Movie id;
    Resources  res;
    View v;
    TypedArray c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    int []img=new int[]{R.drawable.cops_poster,
            R.drawable.eat_sleep_die_poster,
            R.drawable.force_majeure_poster,
            R.drawable.four_shades_of_brown_poster,
            R.drawable.haxan_poster,
            R.drawable.let_the_right_one_in_poster,
            R.drawable.my_life_as_a_dog_poster,
            R.drawable.persona_poster,
            R.drawable.show_me_love_poster,
            R.drawable.you_the_living_poster};



    public masterScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v  = inflater.inflate(R.layout.fragment_master_screen, container, false);
        listView=(ListView)v.findViewById(R.id.list1);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
         Init();
        Log.i("Initialize ", "Year1 " + year[0]);
                for (int i = 0; i < 10; i++) {
                    id = new Movie();
                    id.setTitle(title[i]);
                    id.setYear(year[i]);
                    id.setImg(img[i]);
                    itemsArrayList.add(id);





                }
                movieAdapter adapter = new movieAdapter(getContext(), itemsArrayList);
                listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                dS = new detailScreen();
                ft.replace(R.id.fragmentContainer, dS);
                ft.addToBackStack(null);
                ft.commit();


                if (listView.getSelectedItemPosition() == 0) {

                    dS.Cops();

                }
                if (listView.getSelectedItemPosition() == 1) {

                    dS.Eat();


                }
                if (listView.getSelectedItemPosition() == 2) {

                    dS.force_majeure();


                }
                if (listView.getSelectedItemPosition()==3) {

                    dS.four_shades_of_brown();


                }
                if (listView.getSelectedItemPosition()==4) {

                    dS.haxan();


                }
                if (listView.getSelectedItemPosition()==5) {
                    dS.let_the_right_one_in();


                }
                if (listView.getSelectedItemPosition()==6) {

                    dS.my_life_as_a_dog();


                }
                if (listView.getSelectedItemPosition()==7) {

                    dS.persona();


                }
                if (listView.getSelectedItemPosition()==8) {

                    dS.show_me_love();


                }
                if (listView.getSelectedItemPosition()==9) {

                    dS.you_the_living();  //Image to big to load


                }
            }


        });
        return v;
    }
    private void Init()
    {
        res=getResources();
        title=new String[10];
        year=new String[10];
        pos=new int[10];

        ///title
        cops=getResources().getStringArray(R.array.cops)[0];
        eat=getResources().getStringArray(R.array.eat_sleep_die)[0];
        force=getResources().getStringArray(R.array.force_majeure)[0];
        four=getResources().getStringArray(R.array.four_shades_of_brown)[0];
        haxan=getResources().getStringArray(R.array.haxan)[0];
        let=getResources().getStringArray(R.array.let_the_right_one_in)[0];
        myLife=getResources().getStringArray(R.array.my_life_as_a_dog)[0];
        persona=getResources().getStringArray(R.array.persona)[0];
        showMeLove=getResources().getStringArray(R.array.show_me_love)[0];
        youTheLiving=getResources().getStringArray(R.array.you_the_living)[0];
        title[0]=cops;
        title[1]=eat;
        title[2]=force;
        title[3]=four;
        title[4]=haxan;
        title[5]=let;
        title[6]=myLife;
        title[7]=persona;
        title[8]=showMeLove;
        title[9]=youTheLiving;

        //year Cops
        c1=res.obtainTypedArray(R.array.cops);
        year[0] =c1.getString(1);

        //year eat_sleep_die
        c2=res.obtainTypedArray(R.array.eat_sleep_die);
        year[1]=c2.getString(1);
        //
        c3=res.obtainTypedArray(R.array.force_majeure);
        year[2]=c3.getString(1);

        c4=res.obtainTypedArray(R.array.four_shades_of_brown);
        year[3]=c4.getString(1);

        c4=res.obtainTypedArray(R.array.haxan);
        year[4]=c4.getString(1);

        c5=res.obtainTypedArray(R.array.let_the_right_one_in);
        year[5]=c5.getString(1);

        c6=res.obtainTypedArray(R.array.my_life_as_a_dog);
        year[6]=c6.getString(1);

        c7=res.obtainTypedArray(R.array.persona);
        year[7]=c7.getString(1);

        c8=res.obtainTypedArray(R.array.show_me_love);
        year[8]=c8.getString(1);
        c9=res.obtainTypedArray(R.array.you_the_living);
        year[9]=c9.getString(1);

    }



}
