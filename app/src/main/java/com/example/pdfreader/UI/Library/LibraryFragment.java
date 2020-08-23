package com.example.pdfreader.UI.Library;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdfreader.FileUtil;
import com.example.pdfreader.R;
import com.example.pdfreader.Utils;
import com.example.pdfreader.domain.Document;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.net.URI;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LibraryFragment extends Fragment {
    private static final int READ_REQUEST_CODE = 100;
    @BindView(R.id.documentsRecyclerView)
    RecyclerView documentsRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    LibraryAdapter adapter;
    LibraryViewmodel viewmodel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_library, container, false);
        ButterKnife.bind(this,view);

        viewmodel = new ViewModelProvider(this)
                .get(LibraryViewmodel.class);
        adapter = new LibraryAdapter(getContext(), this::openPdf);
        documentsRecyclerView.setAdapter(adapter);

        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            startActivityForResult(intent
                    , READ_REQUEST_CODE);
        });
        viewmodel.getDocs();
        setObservers();
        return view;
    }
    void setObservers(){
        LifecycleOwner owner=getViewLifecycleOwner();
        viewmodel.documentsLiveData.observe(owner,
                documents -> adapter.updateData(documents));
        viewmodel.errorWhileGettingDocs.observe(owner,
                error->{
                    if(error){
                        Toast.makeText(getContext(),"error while loading data",
                                Toast.LENGTH_LONG).show();
                    }
                });
        viewmodel.documentAdded.observe(owner,
                added->{
                    if(added){
                        Toast.makeText(getContext(),"document added successfully",
                                Toast.LENGTH_LONG).show();
                        viewmodel.getDocs();
                    }else{
                        Toast.makeText(getContext(),"error ",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void openPdf(Document document){
        Log.d("abcde","openpdf");
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+document.getTitle());
        Log.d("abcde",file.getAbsolutePath());
        Log.d("abcde",document.getTitle());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(FileProvider.getUriForFile(getContext()
                , getContext()
                        .getApplicationContext().getPackageName() +
                        ".provider", file)
                , "application/pdf");
     //   intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri=data.getData();
            viewmodel.addDocument(FileUtil.getDocumentDetails(getContext(),uri.toString()));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
