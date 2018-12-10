package ee461l.stockapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView article;
    private ImageView icon;
    private View itemView;
    private ViewGroup parent;

    public NewsViewHolder(View v, ViewGroup parent){
        super(v);
        article = v.findViewById(R.id.newsUrl);
        icon = v.findViewById(R.id.thumbnail);
        this.itemView = v;
        this.parent = parent;
    }

    public void applyContent(String textURL, Bitmap image){

        article.setText(Html.fromHtml(textURL, Html.FROM_HTML_MODE_LEGACY));
        article.setMovementMethod(LinkMovementMethod.getInstance());
        icon.setImageBitmap(image);
        int firstQuote = textURL.indexOf('"') + 1;
        int secondQuote = textURL.indexOf('"',firstQuote);
        String linkUrl = textURL.substring(firstQuote,secondQuote);
        itemView.setOnClickListener(new LinkListener(linkUrl));
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
