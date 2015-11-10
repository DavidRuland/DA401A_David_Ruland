package se.mah.ac9511.assignment3;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class quoteFragment extends Fragment {
    private View v;
    FloatingActionButton fab;
    ListView listView;
    Context c=getContext();
    private TextView text;
    private ProgressBar pb;
    DownloadString dLS1,dLS2,dLS3;
    String q1;
    String q2;
    String q3;
    quote q;
    customAdapter ad;
    int myDownload;
    String[] quotesList;
    Integer[] imgid={R.drawable.ic_control_point_white_48dp,R.drawable.ic_control_point_white_48dp,R.drawable.ic_control_point_white_48dp};
    ArrayList<quote> itemsArrayList=new ArrayList<quote>();

    String url="https://api.github.com/zen?access_token=5ad99b894485cceb8cf42d3156bd05c95077d60e";

    public quoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_quote, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        q = new quote();
        q.setQuote1(q1);
        q.setQuote2(q2);
        q.setQuote3(q3);
        q.setImg(imgid[1]);
        itemsArrayList.add(q);
        ad=new customAdapter(getContext(),itemsArrayList);
        ad.notifyDataSetChanged();
        listView.setAdapter(ad);
        listView.setVisibility(View.INVISIBLE);

        fab=(FloatingActionButton)v.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager conn = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = conn.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {


                    dLS1=  new DownloadString(q.getQuote1());
                    dLS1.execute(url);
                    dLS2 =  new DownloadString(q.getQuote2());
                    dLS2.execute(url);
                    dLS3 =  new DownloadString(q.getQuote3());
                    dLS3.execute(url);

                } else {
                    Toast.makeText(getContext(), "download error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }
    private class DownloadString  extends AsyncTask<String, Void,String> {
//        public   String quote1,quote2,quote3;
        private PowerManager.WakeLock mWakeLock;
        public DownloadString(String result) {
            q1=result;
            q2=result;
            q3=result;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            pb = (ProgressBar) v.findViewById(R.id.progressBar);
            pb.setVisibility(View.VISIBLE);
            text = (TextView)v.findViewById(R.id.textView);
            text.setText("Downloading quotes");
            myDownload = 0;
            PowerManager pm = (PowerManager)getContext().getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            Log.i("quoteFragment", "onPreExecute");

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
            Log.i("quoteFragment", "readStream");
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
                Log.i("quoteFragment", "doInBackground"+urlConnection.getResponseCode());
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
            Log.i("quoteFragment", "publishProgress: "+values);
        }


        protected void onPostExecute(String result) {

//            quotesList = new String[3];
//             quotesList[0] = result;
//             quotesList[1] = result;
//             quotesList[2] =result;

            mWakeLock.release();
            listView.setVisibility(View.VISIBLE);
            if (result != null)
            {
                Toast.makeText(getContext(),"Quotes downloaded: "+result, Toast.LENGTH_LONG).show();
                Log.i("onPostExcecute", "Resultat "+result);
                text.setText("Quotes downloaded");
            }







        }


    }

}

