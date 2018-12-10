package ee461l.stockapp;

import com.jjoe64.graphview.series.DataPoint;
import java.util.Date;

public class GraphData {

    @SuppressWarnings("CanBeFinal")
    private int width;
    private Date[] dataDates;
    private double[] closingData;

    public GraphData(int width){
        this.width = width;
        dataDates = new Date[width];
        closingData = new double[width];
    }

    public void addDataPoint(Date date, double data, int index){
        dataDates[index] = date;
        closingData[index] = data;
    }

    public DataPoint[] getGraphData(){
        DataPoint[] array = new DataPoint[width];
        for(int i = 0; i < width; i++){
            array[i] = new DataPoint(dataDates[i],closingData[i]);
        }
        return (array);
    }

    public long getMinX(){
        return(dataDates[0].getTime());
    }

    public long getMaxX(){
        return (dataDates[width-1].getTime());
    }

    public Date[] getDataDates() {
        return dataDates;
    }

    public void setDataDates(Date[] dataDates) {
        this.dataDates = dataDates;
    }

    public double[] getClosingData() {
        return closingData;
    }

    public void setClosingData(double[] closingData) {
        this.closingData = closingData;
    }

}
