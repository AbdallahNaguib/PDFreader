package com.example.pdfreader.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pdfreader.DB.Tables.BookmarkEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BookmarkDAO {
    @Insert(onConflict = REPLACE)
    Completable insert(BookmarkEntity entity);

    @Query("SELECT * FROM bookmarks where documentUri=:documentUri")
    Single<List<BookmarkEntity>> getBookmarks(String documentUri);

    @Delete
    Completable removeBookmark(BookmarkEntity entity);
}
