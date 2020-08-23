package com.example.pdfreader.domain;

public class Bookmark {
    private int id=0;
    private int page;

    public Bookmark(int id, int page) {
        this.id = id;
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
