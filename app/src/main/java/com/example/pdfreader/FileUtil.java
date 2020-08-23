package com.example.pdfreader;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import com.example.pdfreader.domain.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private static String getPdfThumbnailUri(Context context,
                                             String documentUri,
                                             String documentName) {
        PdfRenderer pdfRenderer = null;
        try {
            pdfRenderer = new PdfRenderer(context.getContentResolver()
                    .openFileDescriptor(Uri.parse(documentUri),
                            "r"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PdfRenderer.Page firstPage = pdfRenderer.openPage(0);

        Bitmap bitmap = Bitmap.createBitmap(
                firstPage.getWidth(),
                firstPage.getHeight(),
                Bitmap.Config.ARGB_8888);

        firstPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        firstPage.close();
        pdfRenderer.close();

        File thumbnailFile = new File(context.getCacheDir()
                , documentName + "_thumbnail");

        try {
            FileOutputStream out = new FileOutputStream(thumbnailFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return thumbnailFile.getAbsolutePath();
    }

    public static Document getDocumentDetails(Context context, String documentUri) {
        String[] projection = {MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.SIZE};

        Cursor metaCursor = context.getContentResolver().query(Uri.parse(documentUri), projection, null, null, null);
        int nameIndex = metaCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = metaCursor.getColumnIndex(OpenableColumns.SIZE);
        if (metaCursor.moveToFirst()) {
            String name = metaCursor.getString(nameIndex);
            return new Document(documentUri,
                    name,
                    metaCursor.getInt(sizeIndex),
                    getPdfThumbnailUri(context,
                            documentUri, name)
            );
        } else {
            return new Document(documentUri,
                    "No name",
                    0,
                    ""
            );
        }
    }
}

