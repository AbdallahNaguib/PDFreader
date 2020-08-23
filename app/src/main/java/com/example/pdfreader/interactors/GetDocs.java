package com.example.pdfreader.interactors;

import android.util.Log;

import com.example.pdfreader.data.repositories.DocumentRepository;
import com.example.pdfreader.domain.Document;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetDocs {
    DocumentRepository documentRepository;

    @Inject
    public GetDocs(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Single<List<Document>> run(){
        Log.d("abcde",documentRepository.toString());
        return documentRepository.getDocs();
    }
}
