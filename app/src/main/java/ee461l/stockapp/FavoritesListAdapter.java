package ee461l.stockapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoritesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] symbols;
    private String[] logoURLs;
    private final View.OnClickListener detailOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, FavoriteDisplay.class);
            InfoContainer info = (InfoContainer) view.getTag();
            intent.putExtra("position", info.position);
            intent.putExtra("symbol", info.symbol);
            context.startActivity(intent);
        }
    };

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
        vh.itemView.setTag(new InfoContainer(position,symbols[position]));
        vh.itemView.setOnClickListener(detailOnClickListener);
    }


    class InfoContainer{

        private int position;
        private String symbol;

        public InfoContainer(int position, String symbol){
            this.position = position;
            this.symbol = symbol;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

}