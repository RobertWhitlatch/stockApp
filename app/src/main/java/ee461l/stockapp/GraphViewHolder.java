package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.LineGraphSeries;

import static ee461l.stockapp.Define.LABEL_ANGLE;

class GraphViewHolder  extends RecyclerView.ViewHolder  {

    private GraphView gv;
    @SuppressWarnings("CanBeFinal")
    private ViewGroup parent;

    public GraphViewHolder(View v, ViewGroup viewGroup) {
        super(v);
        this.gv = v.findViewById(R.id.graph_holder);
        this.parent = viewGroup;
    }

    public GraphView getGraphView() {
        return gv;
    }

    public void setGraphView(GraphView gv) {
        this.gv = gv;
    }

    public void applyGraph(GraphData dataSource){
        gv.addSeries(new LineGraphSeries<>(dataSource.getGraphData()));

        // set date label formatter
        gv.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(parent.getContext()));
        gv.getGridLabelRenderer().setNumHorizontalLabels(7); // only 7 because of the space
        gv.getGridLabelRenderer().setHumanRounding(false);
        gv.getGridLabelRenderer().setHorizontalLabelsAngle(LABEL_ANGLE);

        // set manual x bounds to have nice steps
        gv.getViewport().setXAxisBoundsManual(true);
        gv.getViewport().setMinX(dataSource.getMinX());
        gv.getViewport().setMaxX(dataSource.getMaxX());
    }

}
