package com.example.pdfreader.interactors;

import com.example.pdfreader.data.repositories.DocumentRepository;
import com.example.pdfreader.domain.Document;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AddDoc {
    DocumentRepository documentRepository;

    @Inject
    public AddDoc(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Completable run(Document document){
        return documentRepository.addDoc(document);
    }
}
