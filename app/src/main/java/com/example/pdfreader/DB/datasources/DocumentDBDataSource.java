package com.example.pdfreader.DB.datasources;

import com.example.pdfreader.DB.DAO.DocumentDAO;
import com.example.pdfreader.DB.EntityMap;
import com.example.pdfreader.DB.Tables.DocumentEntity;
import com.example.pdfreader.data.datasources.DocumentDataSource;
import com.example.pdfreader.domain.Document;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DocumentDBDataSource implements DocumentDataSource {
    @Inject
    DocumentDAO documentDAO;
    @Inject
    public DocumentDBDataSource(DocumentDAO documentDAO){
        this.documentDAO = documentDAO;
    }

    @Override
    public Completable add(Document document) {
        return documentDAO.insert(EntityMap.mapToTable(document));
    }

    @Override
    public Single<List<Document>> getDocs() {
        return documentDAO.getDocuments().map(documentEntities -> {
            List<Document> documents=new ArrayList<>();
            for (DocumentEntity entity:documentEntities) {
                documents.add(EntityMap.mapToDomain(entity));
            }
            return documents;
        });
    }

    @Override
    public Completable delete(Document document) {
        return documentDAO.removeDocument(EntityMap.mapToTable(document));
    }
}
