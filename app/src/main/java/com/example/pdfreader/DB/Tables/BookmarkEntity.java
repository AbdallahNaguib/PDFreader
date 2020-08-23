package com.example.pdfreader.DB.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class BookmarkEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    int id=0;
    @ColumnInfo(name = "documentUri")
    String documentUri;
    @ColumnInfo(name = "page")
    int page;

    public BookmarkEntity(int id, String documentUri, int page) {
        this.id = id;
        this.documentUri = documentUri;
        this.page = page;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentUri() {
        return documentUri;
    }

    public void setDocumentUri(String documentUri) {
        this.documentUri = documentUri;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
