package com.example.simplenews.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simplenews.MainActivity;
import com.example.simplenews.R;
import com.example.simplenews.models.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/*name (news source)
 * title (headline)
 * description (content)
 * url to image (imageView)
 * url
 * published time
 * */
public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.Viewholder> {
    private ArrayList<Article> newsArticles = new ArrayList<>();
    private Context context;

    public NewsArticleAdapter(List<Article> newsArticles, Context context) {
        this.newsArticles = (ArrayList<Article>) newsArticles;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Article currentArticle = newsArticles.get(position);

        holder.newsHeadline.setText(currentArticle.getTitle());
        Glide.with(context).load(currentArticle.getUrlToImage()).into(holder.newsImage);
        holder.newsContent.setText(currentArticle.getContent());
//        holder.publishedTime.setText(UTCtoLocalDateConverter(currentArticle.getPublishedAt()));


    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView newsHeadline;
        TextView newsContent;
//        TextView publishedTime;
        ImageView newsImage;
        ConstraintLayout parentLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            newsHeadline = itemView.findViewById(R.id.news_headline_textView);
            newsContent = itemView.findViewById(R.id.news_content_textView);
//            publishedTime = itemView.findViewById(R.id.published_time_textView);
            newsImage = itemView.findViewById(R.id.news_image_view);
            parentLayout = itemView.findViewById(R.id.list_item_parent_layout);

        }
    }
/*
* Helper method to convert the time string in UTC to a local readable date string
//* */
//    private String UTCtoLocalDateConverter(String timeinUTC) {
//
//        String dateStr = timeinUTC;
//        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date date = null;
//        try {
//            date = df.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Log.d("RecyclerViewAdapter", "Error formatting date");
//        }
//        df.setTimeZone(TimeZone.getDefault());
//        String formattedDate = df.format(date);
//        return formattedDate;
//    }



}
