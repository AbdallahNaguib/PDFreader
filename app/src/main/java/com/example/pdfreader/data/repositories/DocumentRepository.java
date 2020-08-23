package com.example.pdfreader.data.repositories;


import android.util.Log;

import com.example.pdfreader.DB.datasources.DocumentDBDataSource;
import com.example.pdfreader.data.datasources.DocumentDataSource;
import com.example.pdfreader.domain.Document;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DocumentRepository {
    DocumentDataSource documentDataSource;

    @Inject
    public DocumentRepository(DocumentDBDataSource documentDataSource) {
        this.documentDataSource = documentDataSource;
    }

    public Single<List<Document>> getDocs(){
        return documentDataSource.getDocs();
    }

    public Completable addDoc(Document document){
        return documentDataSource.add(document);
    }

    public Completable delete(Document document){
        return documentDataSource.delete(document);
    }

}
