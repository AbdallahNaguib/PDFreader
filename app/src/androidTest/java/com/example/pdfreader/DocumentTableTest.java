package com.example.pdfreader;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.pdfreader.DB.DAO.DocumentDAO;
import com.example.pdfreader.DB.RoomDB;
import com.example.pdfreader.DB.Tables.DocumentEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DocumentTableTest {
    private DocumentDAO documentDAO;
    private RoomDB db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, RoomDB.class).build();
        documentDAO = db.documentDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertDocument_checkInserted(){
        DocumentEntity documentEntity=new DocumentEntity();
        documentEntity.setSize(10);
        documentEntity.setTitle("title");
        documentEntity.setThumbnailUri("thumb");
        documentEntity.setUri("uri");
        Assert.assertNotNull(documentDAO);
        documentDAO.insert(documentEntity);
        getDocs();
    }
    void getDocs(){
        documentDAO.getDocuments().subscribeOn(Schedulers.computation())
                .subscribeWith(new DisposableSingleObserver<List<DocumentEntity>>() {
                    @Override
                    public void onSuccess(List<DocumentEntity> documentDAOS) {
                        Assert.fail();
                        Assert.assertEquals(documentDAOS.size(),0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Assert.fail();
                    }
                });
    }
}
