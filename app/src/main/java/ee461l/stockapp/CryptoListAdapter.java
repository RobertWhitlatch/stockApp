package ee461l.stockapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class CryptoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @SuppressWarnings("CanBeFinal")
    private String[] symbols;
    private final View.OnClickListener detailOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, CryptoDisplay.class);
            Integer position = (Integer) view.getTag();
            intent.putExtra("position", position);
            context.startActivity(intent);
        }
    };

    public CryptoListAdapter(String[] symbols) {
        this.symbols = symbols;
    }

    @Override
    public int getItemCount() {
        return symbols.length;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.text_view_holder, viewGroup, false);
        return (new TextViewHolder(v));
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TextViewHolder vh = (TextViewHolder) viewHolder;
        vh.applyText(symbols[position]);
        vh.itemView.setTag(position);
        vh.itemView.setOnClickListener(detailOnClickListener);
    }

}
