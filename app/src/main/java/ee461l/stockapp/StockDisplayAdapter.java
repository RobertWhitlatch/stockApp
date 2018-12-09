package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StockDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] displaySet;
    private GraphData graphData;
    private String symbol;
    private final int GRAPH = 0;
    private final int IMAGE = 1;
    private final int TEXT = 2;

    public StockDisplayAdapter(SearchInfo info) {
        this.displaySet = info.getDisplaySet();
        this.graphData = info.fetchGraphData();
        this.symbol = info.getSymbol();
    }

    @Override
    public int getItemCount() {
        return displaySet.length;
    }

    @Override
    public int getItemViewType(int position) {
        switch(position){
            case 0:
                return (GRAPH);
            case 1:
                return (IMAGE);
            default:
                return (TEXT);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v;
        switch(viewType){
            case GRAPH:
                v = inflater.inflate(R.layout.graph_view_holder, viewGroup, false);
                return(new GraphViewHolder(v,viewGroup, symbol));
            case IMAGE:
                v = inflater.inflate(R.layout.image_view_holder, viewGroup, false);
                return(new ImageViewHolder(v,viewGroup));
            case TEXT:
            default:
                v = inflater.inflate(R.layout.text_view_holder, viewGroup, false);
                return(new TextViewHolder(v));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch(viewHolder.getItemViewType()){
            case GRAPH:
                GraphViewHolder gvh = (GraphViewHolder) viewHolder;
                gvh.applyGraph(graphData);
                break;
            case IMAGE:
                ImageViewHolder ivh = (ImageViewHolder) viewHolder;
                ivh.applyImage(displaySet[position-1]);
                break;
            case TEXT:
            default:
                TextViewHolder tvh = (TextViewHolder) viewHolder;
                tvh.applyText(displaySet[position-1]);
                break;

        }

    }

}