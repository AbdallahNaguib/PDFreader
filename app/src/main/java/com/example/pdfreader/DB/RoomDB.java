package com.example.pdfreader.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pdfreader.DB.DAO.BookmarkDAO;
import com.example.pdfreader.DB.DAO.DocumentDAO;
import com.example.pdfreader.DB.Tables.BookmarkEntity;
import com.example.pdfreader.DB.Tables.DocumentEntity;

@Database(entities = {DocumentEntity.class, BookmarkEntity.class}
,version = 2)
public abstract class RoomDB extends RoomDatabase {
    private static String DATABASE_NAME = "pdf.db";
    private static RoomDB instance = null;

    private static RoomDB create(Context context) {
        return Room.databaseBuilder(context, RoomDB.class, DATABASE_NAME)
            .fallbackToDestructiveMigration()
                .build();
    }

    public static RoomDB getInstance(Context context){
        if(instance==null){
            instance=create(context);
        }
        return instance;
    }
    public abstract DocumentDAO documentDAO();
    public abstract BookmarkDAO bookmarkDAO();
}
