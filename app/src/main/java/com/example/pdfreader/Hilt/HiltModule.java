package com.example.pdfreader.Hilt;

import android.content.Context;

import com.example.pdfreader.DB.DAO.BookmarkDAO;
import com.example.pdfreader.DB.DAO.DocumentDAO;
import com.example.pdfreader.DB.RoomDB;
import com.example.pdfreader.DB.datasources.BookmarkDBDataSource;
import com.example.pdfreader.DB.datasources.DocumentDBDataSource;
import com.example.pdfreader.UI.Library.LibraryViewmodel;
import com.example.pdfreader.data.datasources.BookmarkDataSource;
import com.example.pdfreader.data.datasources.DocumentDataSource;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@InstallIn(ApplicationComponent.class)
@Module
public class HiltModule {
    @Provides
    DocumentDAO getDocumentDAO(@ApplicationContext Context context){
        return RoomDB.getInstance(context).documentDAO();
    }

    @Provides
    BookmarkDAO getBookmarkDAO(@ApplicationContext Context context){
        return RoomDB.getInstance(context).bookmarkDAO();
    }
}

