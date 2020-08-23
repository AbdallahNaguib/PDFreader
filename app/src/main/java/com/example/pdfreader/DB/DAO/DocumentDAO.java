package com.example.pdfreader.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pdfreader.DB.Tables.DocumentEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DocumentDAO {

    @Insert(onConflict = REPLACE)
    Completable insert(DocumentEntity entity);

    @Query("SELECT * FROM documents")
    Single<List<DocumentEntity>> getDocuments();

    @Delete
    Completable removeDocument(DocumentEntity document);
}
