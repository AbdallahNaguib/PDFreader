package com.example.pdfreader.data.datasources;


import com.example.pdfreader.domain.Bookmark;
import com.example.pdfreader.domain.Document;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkDataSource {
    Completable add(Bookmark bookmark, Document document);
    Single<List<Bookmark>> getBookmarks(Document document);
    Completable delete(Document document,Bookmark bookmark);
}
