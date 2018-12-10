package ee461l.stockapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @SuppressWarnings("CanBeFinal")
    private NewsContainer newsContainer;

    public NewsListAdapter(NewsContainer newsContainer){
        this.newsContainer = newsContainer;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.single_news_article, viewGroup, false);
        return (new NewsViewHolder(v));

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        NewsViewHolder vh = (NewsViewHolder) viewHolder;
        vh.applyContent(newsContainer.getUrlAtIndex(position), newsContainer.getBitmapAtIndex(position));
    }

    @Override
    public int getItemCount() {
        return newsContainer.size();
    }


}