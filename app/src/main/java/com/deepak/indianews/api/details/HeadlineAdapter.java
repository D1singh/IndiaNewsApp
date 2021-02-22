package com.deepak.indianews.api.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deepak.indianews.R;

import java.util.ArrayList;
import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.HeadViewHolder> {
    List<Article> articles = new ArrayList<>();
    Context context;

    public HeadlineAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public HeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_card_view, parent, false);
        return new HeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.title_of_the_news.setText(article.getTitle());
        Glide.with(context)
                .asBitmap()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.reasons)
                .into(holder.news_image);
        holder.news_source_name.setText(article.getSource().getName());
        holder.article_author_name.setText(article.getAuthor());
        holder.news_published_date.setText(article.getPublishedAt());
        holder.article_description.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView title_of_the_news, news_source_name, article_author_name, news_published_date, article_description;
        ImageView news_image;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            title_of_the_news = itemView.findViewById(R.id.title_of_the_news);
            news_source_name = itemView.findViewById(R.id.news_source_name);
            article_author_name = itemView.findViewById(R.id.article_author_name);
            news_published_date = itemView.findViewById(R.id.news_published_date);
            article_description = itemView.findViewById(R.id.article_description);
            news_image = itemView.findViewById(R.id.news_image);
        }
    }
}
