package com.example.simplenews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simplenews.R;
import com.example.simplenews.models.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*name (news source)
 * title (headline)
 * description (content)
 * url to image (imageView)
 * url
 * published time
 * */
public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.Viewholder> {
    private ArrayList<Article> newsArticles;
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

        Glide.with(context)
                .load(currentArticle.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.newsImage);

        holder.newsHeadline.setText(currentArticle.getTitle());
        holder.newsContent.setText(currentArticle.getContent());
        holder.publishedTime.setText(convertUtc2Local(currentArticle.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView newsHeadline;
        TextView newsContent;
        TextView publishedTime;
        ImageView newsImage;
        ConstraintLayout parentLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            newsHeadline = itemView.findViewById(R.id.news_headline_textView);
            newsContent = itemView.findViewById(R.id.news_content_textView);
            publishedTime = itemView.findViewById(R.id.published_time_textView);
            newsImage = itemView.findViewById(R.id.news_image_view);
            parentLayout = itemView.findViewById(R.id.list_item_parent_layout);

        }


    }

    /*
     * Helper method to convert a UTC String to a locale date formatted String
     * */
    public static String convertUtc2Local(String utcTime) {
        String convertedTime = utcTime;
        String pattern = "MMMM d, yyyy";
        Locale locale = Locale.getDefault();
        /*TODO Fix this it just shows todays date not the actual date string*/
        Date date = new Date();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        convertedTime = simpleDateFormat.format(date);
        return convertedTime;

    }

    // Clean all elements of the recycler
    public void clearNewsArticles() {
        newsArticles.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Article> list) {
        newsArticles.addAll(list);
        notifyDataSetChanged();
    }
}

