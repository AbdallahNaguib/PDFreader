package com.example.pdfreader.DB.datasources;

import com.example.pdfreader.DB.DAO.BookmarkDAO;
import com.example.pdfreader.DB.EntityMap;
import com.example.pdfreader.DB.Tables.BookmarkEntity;
import com.example.pdfreader.data.datasources.BookmarkDataSource;
import com.example.pdfreader.domain.Bookmark;
import com.example.pdfreader.domain.Document;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BookmarkDBDataSource implements BookmarkDataSource {
    @Inject
    BookmarkDAO bookmarkDAO;

    @Inject
    public BookmarkDBDataSource(BookmarkDAO dao){
        bookmarkDAO = dao;
    }

    @Override
    public Completable add(Bookmark bookmark, Document document) {
        return bookmarkDAO.insert(EntityMap.mapToTable(bookmark,document));
    }

    @Override
    public Single<List<Bookmark>> getBookmarks(Document document) {
        return bookmarkDAO.getBookmarks(document.getUri()).map(bookmarkEntities -> {
            List<Bookmark> bookmarks=new ArrayList<>();
            for (BookmarkEntity entity:bookmarkEntities) {
                bookmarks.add(EntityMap.mapToDomain(entity));
            }
            return bookmarks;
        });
    }

    @Override
    public Completable delete(Document document, Bookmark bookmark) {
        return bookmarkDAO.removeBookmark(EntityMap.mapToTable(bookmark,document));
    }
}
