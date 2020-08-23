package com.example.pdfreader.data.datasources;

import com.example.pdfreader.domain.Document;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DocumentDataSource {
    Completable add(Document document);
    Single<List<Document>> getDocs();
    Completable delete(Document document);
}
