package com.example.pdfreader.DB.Tables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "documents")
public class DocumentEntity {
    @ColumnInfo(name = "uri")
    @PrimaryKey
    @NonNull
    String uri="";
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "size")
    int size;

    public DocumentEntity(@NonNull String uri, String title, int size, String thumbnailUri) {
        this.uri = uri;
        this.title = title;
        this.size = size;
        this.thumbnailUri = thumbnailUri;
    }

    @ColumnInfo(name = "thumbnailUri")
    String thumbnailUri;

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
