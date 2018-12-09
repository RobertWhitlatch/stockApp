package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MitchellNewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Article[] articles;

    public MitchellNewsListAdapter(Article[] articles){
        this.articles = articles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.news_view_holder, viewGroup, false);
        return (new NewsViewHolder(v, viewGroup));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        NewsViewHolder vh = (NewsViewHolder) viewHolder;
        vh.applyContent(articles[position]);
    }

    @Override
    public int getItemCount() {
        return articles.length;
    }

}
