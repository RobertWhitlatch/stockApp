package ee461l.stockapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class CryptoDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    @SuppressWarnings("CanBeFinal")
    private String[] displaySet;

    public CryptoDisplayAdapter(String[] displaySet) {
        this.displaySet = displaySet;
    }

    @Override
    public int getItemCount() {
        return displaySet.length;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.text_view_holder, viewGroup, false);
        viewHolder = new TextViewHolder(v);
        return (viewHolder);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            TextViewHolder vh = (TextViewHolder) viewHolder;
            vh.applyText(displaySet[position]);
    }

}
