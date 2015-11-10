package se.mah.ac9511.assignment5;

import android.app.ActionBar;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import android.os.Debug;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class quoteFragment extends Fragment {
    private View v;
    FloatingActionButton fab;
    ListView listView;
    Toolbar tb;
    ArrayList<quote> itemsArrayList = new ArrayList<quote>();
    quote q;
    Integer[] imgid = {R.drawable.ic_control_point_white_48dp, R.drawable.ic_control_point_white_48dp, R.drawable.ic_control_point_white_48dp};
    quoteAdapter ad;
    Context c = getContext();
    private TextView text;
    private ProgressBar pb;
    Random r = new Random();
    DownloadString dLS1,dLS2,dLS3;
    String  quote1, quote2, quote3;
    private int nr=0;
    private ActionMode mActionMode;
    String url="https://api.github.com/zen?access_token=5ad99b894485cceb8cf42d3156bd05c95077d60e";
    int counter = 0;

    public quoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_quote, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setVisibility(View.INVISIBLE);
        q = new quote();
        for (int i=0; i<1;i++)
        {
            q.setQuote1(quote1);
            q.setQuote2(quote2);
            q.setQuote3(quote3);
            q.setImg(imgid[i]);
            itemsArrayList.add(q);
        }
        ad=new quoteAdapter(getContext(),itemsArrayList);
        listView.setAdapter(ad);
        ad.notifyDataSetChanged();
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                if (checked)
                {
                    nr++;
                    ad.setNewNode(position);
                    Toast.makeText(getContext(), "Quotes checked " + nr, Toast.LENGTH_LONG).show();
                }
                else {
                    ad.removeNode(position);
                }
                mode.setTitle(nr + " selected");








            }














            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                int pos=listView.getCheckedItemCount();

                switch (item.getItemId()) {
                    case R.id.delete:
                        nr=0;
                        ad.clear();
                        mode.finish(); // Action picked, so close the CAB
                        ad.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Quotes deleted: " + nr, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.add:
                        if (listView.getCheckedItemCount() == ad.getItemId(0))
                        {

                            dLS1 = new DownloadString(quote1);
                            dLS1.execute(url);



                        }
                        if (listView.getCheckedItemCount() == ad.getItemId(1)) {

                            dLS2 = new DownloadString(quote2);
                            dLS2.execute(url);

                        }
                        if (listView.getCheckedItemCount() == ad.getItemId(2)) {

                            dLS3 = new DownloadString(quote3);
                            dLS3.execute(url);

                        }
                        ad.notifyDataSetChanged();
                        mode.finish(); // Action picked, so close the CAB
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                nr=0;
                mode.setTitle("Select Items");
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.optionsmenu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
                ad.clear();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,android.R.id.text1, quotesList);
        //listView.setAdapter(adapter);
        fab = (FloatingActionButton) v.findViewById(R.id.fab2);
        tb = (Toolbar) v.findViewById(R.id.toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager conn = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = conn.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {


                    dLS1=  new DownloadString(quote1);
                    dLS1.execute(url);
                    dLS2 =  new DownloadString(quote2);
                    dLS2.execute(url);
                    dLS3 =  new DownloadString(quote3);
                    dLS3.execute(url);





                } else {
                    Toast.makeText(getContext(), "download error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }



    private class DownloadString extends AsyncTask<String, Void, String> {
        int myDownload;
        private int nr = 0;
        String contentString;
        public   String q1,q2,q3;
        private PowerManager.WakeLock mWakeLock;

        public DownloadString(String result) {

            q1 = result;
            q3 = result;
            q2 = result;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            PowerManager pm = (PowerManager)getContext().getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            pb = (ProgressBar) v.findViewById(R.id.progressBar);
            pb.setVisibility(View.VISIBLE);
            text = (TextView) v.findViewById(R.id.textView);
            text.setText("Downloading quotes");
            myDownload = 0;

        }


        public String readStream(InputStream inputStream) {

            Reader r = null;
            try {
                r = new InputStreamReader(inputStream, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            char[] buffer = new char[1024];
            try {
                if (r != null) {
                    r.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new String(buffer);
        }

        @Override
        protected String doInBackground(String... sUrl) {
            while (myDownload < 100) {
                myDownload++;
                publishProgress(myDownload);
                SystemClock.sleep(100);
            }

            URL url = null;
            InputStream iS = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(sUrl[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                iS = new BufferedInputStream(urlConnection.getInputStream());
                String contentString = readStream(iS);
                return contentString;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (urlConnection != null) {

                    urlConnection.disconnect();
                }

            }
            return null;
        }


        private void publishProgress(int values) {
            pb.setProgress(values);
        }


        protected void onPostExecute(String result) {

//            quotesList = new String[3];
//             quotesList[0] = result;
//             quotesList[1] = result;
//             quotesList[2] =result;


            mWakeLock.release();
            listView.setVisibility(View.VISIBLE);
            ad.notifyDataSetChanged();
            if (result != null)
                Toast.makeText(getContext(),"Quotes downloaded: "+result, Toast.LENGTH_LONG).show();
            text.setText("Quotes downloaded");
        }

    }


}
