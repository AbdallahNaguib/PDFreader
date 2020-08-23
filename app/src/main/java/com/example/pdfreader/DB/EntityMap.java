package com.example.pdfreader.DB;

import com.example.pdfreader.DB.Tables.BookmarkEntity;
import com.example.pdfreader.DB.Tables.DocumentEntity;
import com.example.pdfreader.domain.Bookmark;
import com.example.pdfreader.domain.Document;

public class EntityMap {
    public static Document mapToDomain(DocumentEntity entity){
        return new Document(entity.getUri(),entity.getTitle(),
                entity.getSize(),entity.getThumbnailUri());
    }

    public static Bookmark mapToDomain(BookmarkEntity entity){
        return new Bookmark(entity.getId(),entity.getPage());
    }

    public static BookmarkEntity mapToTable(Bookmark bookmark,Document document){
        return new BookmarkEntity(bookmark.getId(),document.getUri()
             ,bookmark.getPage());
    }

    public static DocumentEntity mapToTable(Document document){
        return new DocumentEntity(document.getUri(),document.getTitle(),
                document.getSize(),document.getThumbnailUri());
    }
}
