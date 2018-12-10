package ee461l.stockapp;

import java.util.*;

public class ServerHandler {

    private static String serverUrl = "http://35.231.35.233:5000/send1/";
    private static String query = "?data=";
    private static HttpHandler http = new HttpHandler();

    public static ArrayList<Double> PredictRequest (double[] data) {
        String url, response;
        ArrayList<Double> ret = new ArrayList<Double>();
        StringBuilder sb = new StringBuilder();
        sb.append(serverUrl);
        sb.append(query);

        sb.append(data[0]);
        for(int i = 1; i < data.length; i++) {
            sb.append("," + data[i]);
        }
        url = sb.toString();
        try {
            response = http.makeServiceCall(url);

            response = response.substring(0, response.length() - 1);
            List<String> temp = Arrays.asList(response.split(" "));

            for (String s : temp) {
                ret.add(Double.parseDouble(s));
            }

        }catch(Exception e){
            return null;
        }
        return ret;
    }
}
