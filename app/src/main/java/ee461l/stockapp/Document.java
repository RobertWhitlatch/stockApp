package ee461l.stockapp;

public class Document {
    public String id;
    public String language;
    public String text;

    public Document(String id, String language, String text){
        this.id = id;
        this.language = language;
        this.text = text;
    }
}