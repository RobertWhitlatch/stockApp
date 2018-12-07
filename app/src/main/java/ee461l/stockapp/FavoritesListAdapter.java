package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoritesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] symbols;
    private String[] logoURLs;

    public FavoritesListAdapter(String[] symbols, String[] logoURLs) {
        this.symbols = symbols;
        this.logoURLs = logoURLs;
    }

    @Override
    public int getItemCount() {
        return symbols.length;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.favorites_view_holder, viewGroup, false);

        return (new FavoritesViewHolder(v,viewGroup));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        FavoritesViewHolder vh = (FavoritesViewHolder) viewHolder;
        vh.applyContent(symbols[position], logoURLs[position]);
    }

}