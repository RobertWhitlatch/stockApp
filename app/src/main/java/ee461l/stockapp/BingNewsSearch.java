package ee461l.stockapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
class SearchResults{
    HashMap<String, String> relevantHeaders;
    String jsonResponse;
    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
    }
}
public class BingNewsSearch {

    // Replace the subscriptionKey string value with your valid subscription key.
    static String subscriptionKey = "059a059e07c94bd8b24dd170ac55b2f0";

    // Verify the endpoint URI.  At this writing, only one endpoint is used for Bing
    // search APIs.  In the future, regional endpoints may be available.  If you
    // encounter unexpected authorization errors, double-check this value against
    // the endpoint for your Bing Web search instance in your Azure dashboard.
    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v7.0/news/search";

    /*
    -uses microsoft bin api to get json of relevant articles
    -returns map of all results
     */
    public static SearchResults SearchNews (String searchQuery) throws Exception {
        // construct URL of search request (endpoint + query string)
        URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // receive JSON body
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();

        // construct result object for return
        SearchResults results = new SearchResults(new HashMap<String, String>(), response);

        // extract Bing-related HTTP headers
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String header : headers.keySet()) {
            if (header == null) continue;      // may have null key
            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                results.relevantHeaders.put(header, headers.get(header).get(0));
            }
        }

        stream.close();
        return results;
    }

    /*
    -json parser for Sentiment Analyzer that returns a block of text
    made of a few news article titles
     */
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonArray docJson = json.getAsJsonArray("value");
        String allHeaders = "";
        String allDescriptions = "";
        int articleNum = 0;
        if(docJson.size()<2){
            articleNum = docJson.size();
        }
        else{
            articleNum = 2;
        }
        for(int i = 0; i < articleNum; i++){
            JsonElement obj = docJson.get(i);
            allHeaders += obj.getAsJsonObject().get("name").toString() + " ";
            allDescriptions += obj.getAsJsonObject().get("description").toString() + " ";

        }
        return allHeaders + allDescriptions;
    }

    /*
    -json parser that returns an array of urls to several news articles
     */
    public static ArrayList<String> prettifyURL(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonArray docJson = json.getAsJsonArray("value");
        String urls = "";
        String title = "";
        String htmlForm = "";
        ArrayList<String> urlList = new ArrayList<String>();
        int articleNum = 0;
        if(docJson.size()<10){
            articleNum = docJson.size();
        }
        else{
            articleNum = 10;
        }
        for(int i = 0; i < articleNum; i++){
            JsonElement obj = docJson.get(i);
            title = obj.getAsJsonObject().get("name").toString();
            urls = obj.getAsJsonObject().get("url").toString();
            title = title.substring(1, title.length()-1);
            htmlForm = "<a href="+urls+" >"+title+"</a>";
            urlList.add(htmlForm);
        }
        return  urlList;
    }

    /*
    -json parser that returns array of urls of thumbnails for sevral news articles
     */
    public static ArrayList<String> prettifyThumbnail(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray docJson = json.getAsJsonArray("value");
        String images = "";
        ArrayList<String> imageList = new ArrayList<String>();
        int articleNum = 0;
        if(docJson.size()<10){
            articleNum = docJson.size();
        }
        else{
            articleNum = 10;
        }
        for(int i = 0; i < articleNum; i++){
            JsonElement obj = docJson.get(i);
            images = null;
            if(obj.getAsJsonObject().has("image")){
                images = obj.getAsJsonObject().get("image").getAsJsonObject().get("thumbnail").getAsJsonObject().get("contentUrl").toString();
            }
            System.out.println(images);
            if(images!=null){
                images = images.substring(1, images.length()-1);
            }
            else{
                //dummy thumbnail
                images = "http://www.agilelaw.com/wp-content/uploads/free-stock-photos-for-law-firms.jpg";
            }
            imageList.add(images);
            // System.out.println(images);

        }
        return  imageList;
    }

}
