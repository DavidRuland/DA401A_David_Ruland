package se.mah.ac9511.assignment2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


/**
 * Created by David on 2015-09-09.
 */
public class movieAdapter extends BaseAdapter {
    ArrayList<Movie> movies = new ArrayList<Movie>();
    LayoutInflater layoutInflater;
    Context context;
    Bitmap bm;

    public movieAdapter(Context context, ArrayList<Movie> m) {
        this.movies = m;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        movieHolder mHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row, null);
            mHolder = new movieHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (movieHolder) convertView.getTag();
        }
        mHolder.title = textViewInfo(convertView, R.id.Title, movies.get(position).getTitle());
        mHolder.year = textViewInfo(convertView, R.id.Year, movies.get(position).getYear());
        mHolder.icon1 = imageInfo(convertView, R.id.icon, movies.get(position).getImg());
        return convertView;
    }

    private TextView textViewInfo(View v, int id, String Text) {
        TextView textView = (TextView) v.findViewById(id);
        textView.setText(Text);
        return textView;
    }

    private ImageView imageInfo(View v, int id, int icon) {
        ImageView imageView = (ImageView) v.findViewById(id);
        imageView.setImageResource(icon);
        //imageView.setImageBitmap(bm);
        return imageView;
    }


    private static class movieHolder {
        TextView year, title,overview;
        ImageView icon1;

        public movieHolder(View v) {
            year = (TextView) v.findViewById(R.id.Year);
            title = (TextView) v.findViewById(R.id.Title);
            icon1 = (ImageView) v.findViewById(R.id.icon);


        }

    }
}
