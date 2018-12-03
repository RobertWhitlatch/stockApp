package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StockDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] displaySet;
    private final int IMAGE = 0;

    public StockDisplayAdapter(String[] displaySet) {
        this.displaySet = displaySet;
    }

    @Override
    public int getItemCount() {
        return displaySet.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return IMAGE;
        else return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if(viewType == IMAGE) {
            View v = inflater.inflate(R.layout.image_view_holder, viewGroup, false);
            viewHolder = new ImageViewHolder(v,viewGroup);
        } else {
            View v = inflater.inflate(R.layout.text_view_holder, viewGroup, false);
            viewHolder = new TextViewHolder(v);
        }
        return (viewHolder);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder.getItemViewType() == IMAGE){
            ImageViewHolder vh = (ImageViewHolder) viewHolder;
            vh.applyImage(displaySet[position]);
        } else {
            TextViewHolder vh = (TextViewHolder) viewHolder;
            vh.applyText(displaySet[position]);
        }

    }

}