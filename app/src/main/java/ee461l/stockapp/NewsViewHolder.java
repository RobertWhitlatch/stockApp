package ee461l.stockapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class NewsViewHolder extends RecyclerView.ViewHolder {

    @SuppressWarnings("CanBeFinal")
    private TextView article;
    @SuppressWarnings("CanBeFinal")
    private ImageView icon;
    @SuppressWarnings("CanBeFinal")
    private View itemView;

    public NewsViewHolder(View v){
        super(v);
        article = v.findViewById(R.id.newsUrl);
        icon = v.findViewById(R.id.thumbnail);
        this.itemView = v;
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

        @SuppressWarnings("CanBeFinal")
        private String url;

        LinkListener(String url){
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
