package com.example.pdfreader.UI.Library;

import android.net.Uri;
import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.pdfreader.domain.Document;
import com.example.pdfreader.interactors.AddDoc;
import com.example.pdfreader.interactors.GetDocs;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LibraryViewmodel extends ViewModel {

    AddDoc addDoc;
    GetDocs getDocs;

    @ViewModelInject
    public LibraryViewmodel(AddDoc addDoc, GetDocs getDocs,@Assisted SavedStateHandle savedStateHandle) {
        Log.d("abcdefg",getDocs.toString());
        this.addDoc = addDoc;
        this.getDocs = getDocs;
    }

    MutableLiveData<List<Document>> documentsLiveData=new MutableLiveData<>();
    MutableLiveData<Boolean> errorWhileGettingDocs=new MutableLiveData<>();

    MutableLiveData<Boolean> documentAdded=new MutableLiveData<>();

    void getDocs(){
        Log.d("abcdefg",getDocs.toString());
        getDocs.run().subscribeOn(Schedulers.computation())
        .subscribeWith(new DisposableSingleObserver<List<Document>>() {
            @Override
            public void onSuccess(List<Document> documents) {
                documentsLiveData.postValue(documents);
            }

            @Override
            public void onError(Throwable e) {
                errorWhileGettingDocs.postValue(true);
            }
        });
    }
    void addDocument(Document document){
        addDoc.run(document).subscribeOn(Schedulers.computation())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        documentAdded.postValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        documentAdded.postValue(false);
                    }
                });
    }
}
