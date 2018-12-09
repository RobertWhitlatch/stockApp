package ee461l.stockapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView title;
    private TextView citation;
    private TextView content;
    private Button link;
    private ViewGroup parent;

    public NewsViewHolder(View v, ViewGroup parent) {
        super(v);
        image = v.findViewById(R.id.news_image);
        title = v.findViewById(R.id.news_title);
        citation = v.findViewById(R.id.news_citation);
        content = v.findViewById(R.id.news_content);
        link = v.findViewById(R.id.news_link);
        this.parent = parent;
    }

    public void applyContent(Article article){
        Picasso.with(parent.getContext()).load(article.getUrlToImage()).into(image);
        title.setText(article.getTitle());
        citation.setText(article.getCitation());
        content.setText(article.getContent());
        link.setOnClickListener(new LinkListener(article.getUrl()));
    }


    private class LinkListener implements View.OnClickListener {

        private String url;

        public LinkListener(String url){
            this.url = url;
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            context.startActivity(browserIntent);
        }
    }
}
