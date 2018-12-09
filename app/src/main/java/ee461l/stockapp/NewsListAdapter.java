package ee461l.stockapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class NewsListAdapter extends BaseAdapter {

    Context context;
    private  ArrayList<String> values;
    private ArrayList<Bitmap> images;

    public NewsListAdapter(Context context, ArrayList<String> values, ArrayList<Bitmap> images){
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_news_article, parent, false);
            viewHolder.article = (TextView) convertView.findViewById(R.id.newsUrl);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.thumbnail);
            result=convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.article.setText(Html.fromHtml(values.get(position), Html.FROM_HTML_MODE_LEGACY));
        viewHolder.article.setMovementMethod(LinkMovementMethod.getInstance());
        viewHolder.icon.setImageBitmap(images.get(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView article;
        ImageView icon;
    }

}