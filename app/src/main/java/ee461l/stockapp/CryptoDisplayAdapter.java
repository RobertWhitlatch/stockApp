package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CryptoDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private String[] displaySet;

    public CryptoDisplayAdapter(String[] displaySet) {
        this.displaySet = displaySet;
    }

    @Override
    public int getItemCount() {
        return displaySet.length;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.text_view_holder, viewGroup, false);
        viewHolder = new TextViewHolder(v);
        return (viewHolder);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            TextViewHolder vh = (TextViewHolder) viewHolder;
            vh.applyText(displaySet[position]);
    }

}
