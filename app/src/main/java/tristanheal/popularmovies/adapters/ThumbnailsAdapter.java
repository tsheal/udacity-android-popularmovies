package tristanheal.popularmovies.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tristanheal.popularmovies.R;
import tristanheal.popularmovies.activities.DetailActivity;
import tristanheal.popularmovies.activities.MainActivity;
import tristanheal.popularmovies.models.MovieModel;


/**
 * Created by tsheal on 17/10/2016.
 */

public class ThumbnailsAdapter extends BaseAdapter
{
    private List<MovieModel> mMovies;
    private Context mContext;

    public ThumbnailsAdapter(Context context) {

        mContext = context;
    }

    public void SetMovies(List<MovieModel> movies) {

        mMovies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return mMovies.size();
    }

    @Override
    public Object getItem(int position) {

        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {

            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            view = inflater.inflate(R.layout.thumbnail_grid_item, parent, false);
            view.setTag(view.findViewById(R.id.thumbnail_imageview));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra(MainActivity.MOVIE_OBJECT, mMovies.get(position));
                mContext.startActivity(i);
            }
        });

        ImageView thumbnailImageView = (ImageView) view.getTag();

        MovieModel movie = mMovies.get(position);
        String imageUrl = mContext.getString(R.string.moviedb_image_base_url) + movie.PosterPath;
        Picasso.with(mContext).load(imageUrl).into(thumbnailImageView);

        return view;

    }
}
