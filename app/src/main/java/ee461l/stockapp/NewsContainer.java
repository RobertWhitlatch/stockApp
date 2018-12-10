package ee461l.stockapp;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class NewsContainer {

    private ArrayList<String> urlList;
    private ArrayList<String> bitmapStringList;
    @SuppressWarnings("CanBeFinal")
    private Bitmap[] bitmaps;
    private int size;

    public NewsContainer(){
        urlList = new ArrayList<>();
        bitmapStringList = new ArrayList<>();
        bitmaps = new Bitmap[10];
    }

    public void calculate(){
        if(urlList.size() == bitmapStringList.size()){
            size = urlList.size();
        } else {
            size = (urlList.size() < bitmapStringList.size()) ? urlList.size() : bitmapStringList.size();
        }
    }

    public String getUrlAtIndex(int index){
        return urlList.get(index);
    }

    public ArrayList<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(ArrayList<String> urlList) {
        this.urlList = urlList;
    }

    public String getBitmapStringAtIndex(int index){
        return bitmapStringList.get(index);
    }

    public ArrayList<String> getBitmapStringList() {
        return bitmapStringList;
    }

    public void setBitmapStringList(ArrayList<String> bitmapStringList) {
        this.bitmapStringList = bitmapStringList;
    }

    public int size() {
        return size;
    }

    public void setBitmapAtIndex(Bitmap bmap, int index){
        bitmaps[index] = bmap;
    }

    public Bitmap getBitmapAtIndex(int index){
        return bitmaps[index];
    }
}
