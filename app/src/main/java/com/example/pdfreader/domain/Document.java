package com.example.pdfreader.domain;

public class Document {
    String uri="";
    String title;
    int size;
    String thumbnailUri;
    public static Document EMPTY = new Document("","",0,"");

    public Document(String uri, String title, int size, String thumbnailUri) {
        this.uri = uri;
        this.title = title;
        this.size = size;
        this.thumbnailUri = thumbnailUri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

}
