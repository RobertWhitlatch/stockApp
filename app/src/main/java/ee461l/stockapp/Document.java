package ee461l.stockapp;

class Document {
    @SuppressWarnings("CanBeFinal")
    public String id;
    @SuppressWarnings("CanBeFinal")
    public String language;
    @SuppressWarnings("CanBeFinal")
    public String text;

    public Document(String id, String language, String text){
        this.id = id;
        this.language = language;
        this.text = text;
    }
}