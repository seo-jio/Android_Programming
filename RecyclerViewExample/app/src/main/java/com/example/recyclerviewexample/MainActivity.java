package com.example.recyclerviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    RecyclerView mRecyclerView = null;
    RecyclerViewAdapter mAdapter = null;
    ArrayList<RecyclerViewItem> mList;
    String[][] movieInfo = {
            {"The Wizard of Oz", "Fantasy", "1939","9.5"},
            {"Citizen Mane", "Mystery", "1941", "9.1"},
            {"ALL About Eve", "Drama", "19568", "9.3"},
            {"The Third Man", "Thriller", "1949", "9.6"},
            {"A Hard Day's Night", "Rock", "1964","8.9"},
            {"Nodern Times", "Comedy", "1936", "9.2"},
            {"Metropolis", "SF","1927","16"},
            {"Metropolis", "SF", "1927","16"},
            {"Metropolis", "SF","1927","10"},
            {"Metropolis", "SF", "1927", "10"}
        };
        Integer[] images = {
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mList = new ArrayList<>();

        mAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 10; i++){
            Drawable mImageDrawable = ResourcesCompat.getDrawable(getResources(), images[i], null);
            addItem(mImageDrawable, movieInfo[i][0], movieInfo[i][1], movieInfo[i][2], movieInfo[i][3]);
        }

        mAdapter.notifyDataSetChanged();
    }

    private void addItem(Drawable icon, String title, String rating, String genre, String year) {
        RecyclerViewItem item = new RecyclerViewItem();
        item.setIconDrawable(icon);
        item.setTitle(title);
        item.setRating(rating);
        item.setGenre(genre);
        item.setYear(year);
        mList.add(item);
    }
}

class RecyclerViewItem {
    private Drawable iconDrawable;
    private String title;
    private String rating;
    private String genre;
    private String year;

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<RecyclerViewItem> mData = null;
    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> data ) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerViewItem item = mData.get(position);
        holder.imageView.setBackground(item.getIconDrawable());
        holder.title.setText(item.getTitle());
        holder.rating.setText(item.getRating());
        holder.genre.setText(item.getGenre());
        holder.year.setText(item.getYear());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView rating;
        private TextView genre;
        private TextView year;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            rating = (TextView) view.findViewById(R.id.rating);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.releaseYear);
        }
    }

}