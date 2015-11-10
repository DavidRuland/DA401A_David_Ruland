package se.mah.ac9511.assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 2015-10-06.
 */
public class customAdapter extends BaseAdapter {
    ArrayList<quote> quotes=new ArrayList<quote>();
    LayoutInflater layoutInflater;
    Context context;

    public customAdapter(Context context, ArrayList<quote> quotes) {
        this.quotes = quotes;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public quote getItem(int position) {
        return quotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        movieHolder mHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.row,null);
            mHolder= new movieHolder(convertView);
            convertView.setTag(mHolder);
        }
        else
        {
            mHolder=(movieHolder)convertView.getTag();
        }
        mHolder.quote1=textViewInfo(convertView, R.id.textView2, quotes.get(position).getQuote1());
        mHolder.quote2=textViewInfo(convertView, R.id.textView3, quotes.get(position).getQuote2());
        mHolder.quote3=textViewInfo(convertView, R.id.textView4, quotes.get(position).getQuote3());

        mHolder.icon1=imageInfo(convertView, R.id.icon, quotes.get(position).getImg());
        mHolder.icon2=imageInfo(convertView,R.id.icon2, quotes.get(position).getImg());
        mHolder.icon3=imageInfo(convertView,R.id.icon3, quotes.get(position).getImg());


        return  convertView;
    }
    private TextView textViewInfo (View v, int id, String Text)
    {
        TextView textView=(TextView)v.findViewById(id);
        textView.setText(Text);
        return textView;
    }
    private ImageView imageInfo (View v, int id,int icon)
    {
        ImageView imageView=(ImageView)v.findViewById(id);
        imageView.setImageResource(icon);
        return  imageView;
    }


    private static class movieHolder {
        TextView quote1,quote2,quote3;
        ImageView icon1,icon2,icon3;

        public movieHolder (View v)
        {
            quote1=(TextView)v.findViewById(R.id.textView2);
            quote2=(TextView)v.findViewById(R.id.textView3);
            quote3=(TextView)v.findViewById(R.id.textView4);
            icon1=(ImageView)v.findViewById(R.id.icon);
            icon2=(ImageView)v.findViewById(R.id.icon2);
            icon3=(ImageView)v.findViewById(R.id.icon3);
        }

    }
}

