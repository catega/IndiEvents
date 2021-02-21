package com.example.indievents.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.indievents.R;
import com.example.indievents.Utils;
import com.example.indievents.pojo.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<Article> articles;
    private Context context;
    public OnItemClickListener onItemClickListener;

    public NewsAdapter(List<Article> articles, Context context){
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder myViewHolder = holders;
        Article article = articles.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(article.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        myViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        myViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(myViewHolder.imgArticle);

        myViewHolder.title.setText(article.getTitle());
        myViewHolder.desc.setText(article.getDescription());
        myViewHolder.source.setText(article.getSource().getName());
        myViewHolder.time.setText(" - " + Utils.DateToTimeFormat(article.getPublishedAt()));
        myViewHolder.date.setText(Utils.DateFormat(article.getPublishedAt()));
        myViewHolder.author.setText(article.getAuthor());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, desc, author, date, source, time;
        ImageView imgArticle;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.txtArticleTitle);
            desc = itemView.findViewById(R.id.txtDescription);
            author = itemView.findViewById(R.id.txtAuthor);
            date = itemView.findViewById(R.id.txtDate);
            source = itemView.findViewById(R.id.txtSource);
            time = itemView.findViewById(R.id.txtTime);
            imgArticle = itemView.findViewById(R.id.imgArticle);
            progressBar = itemView.findViewById(R.id.progressbarNews);
            this.onItemClickListener = onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            //onItemClickListener.onItemClick(v, getAdapterPosition());
        }


    }
}
