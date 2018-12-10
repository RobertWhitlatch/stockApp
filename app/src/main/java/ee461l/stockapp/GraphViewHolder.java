package ee461l.stockapp;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        new AsyncUpdateGraph().execute(dataSource);
    }

    public void addPredictionLine(GraphData dataSource){
        gv.addSeries(new LineGraphSeries<>(dataSource.getGraphData()));
    }

    class AsyncUpdateGraph extends AsyncTask<GraphData, Integer, ArrayList<Double>> {

        GraphData data; //needed to get information about date
        private int numOfPredictions = 7;
        protected ArrayList<Double> doInBackground(GraphData... data) {
            this.data = data[0];
            return ServerHandler.PredictRequest(data[0].getClosingData());
        }
        protected void onPostExecute(ArrayList<Double> result) {
            if(result == null){
                return; //don't do anything if it' cant get anything from the server
            }
            GraphData predictedData = new GraphData(numOfPredictions);
            Date currentDate = data.getDataDates()[data.getDataDates().length - 1];
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            for(int i = 0; i < numOfPredictions; i++){
                c.add(Calendar.DATE, 1);
                predictedData.addDataPoint(c.getTime(), result.get(i), i );
            }
            addPredictionLine(predictedData);
            gv.getViewport().setMaxX(predictedData.getMaxX());
        }
    }
}
