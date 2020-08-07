package com.example.simplenews.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.simplenews.R;
import com.example.simplenews.models.Article;
import com.victor.loading.rotate.RotateLoading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

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
        holder.rotateLoadingindicator.start();

        Glide.with(context)
                .load(currentArticle.getUrlToImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Timber.d("There was no image in this article");
                        holder.rotateLoadingindicator.stop();
                        holder.rotateLoadingindicator.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.rotateLoadingindicator.stop();
                        holder.rotateLoadingindicator.setVisibility(View.GONE);
                        return false;
                    }
                })
                .error(R.drawable.ic_launcher_foreground)
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
        RotateLoading rotateLoadingindicator;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            newsHeadline = itemView.findViewById(R.id.news_headline_textView);
            newsContent = itemView.findViewById(R.id.news_content_textView);
            publishedTime = itemView.findViewById(R.id.published_time_textView);
            newsImage = itemView.findViewById(R.id.news_image_view);
            parentLayout = itemView.findViewById(R.id.list_item_parent_layout);
            rotateLoadingindicator = itemView.findViewById(R.id.rotate_loading_indicator);

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

