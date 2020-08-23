package com.example.pdfreader.data.repositories;

import com.example.pdfreader.DB.datasources.BookmarkDBDataSource;
import com.example.pdfreader.data.datasources.BookmarkDataSource;
import com.example.pdfreader.domain.Bookmark;
import com.example.pdfreader.domain.Document;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BookmarkRepository {
    BookmarkDataSource bookmarkDataSource;

    @Inject
    public BookmarkRepository(BookmarkDBDataSource bookmarkDataSource) {
        this.bookmarkDataSource = bookmarkDataSource;
    }
    public Completable addBookmark(Bookmark bookmark, Document document){
        return bookmarkDataSource.add(bookmark,document);
    }
    public Single<List<Bookmark>> getBookmarks(Document document){
        return bookmarkDataSource.getBookmarks(document);
    }
    public Completable deleteBookmark(Bookmark bookmark,Document document){
        return bookmarkDataSource.delete(document,bookmark);
    }
}
