package com.example.pdfreader.UI.Library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.bumptech.glide.Glide;
import com.example.pdfreader.R;
import com.example.pdfreader.StringUtils;
import com.example.pdfreader.domain.Document;

import java.util.ArrayList;
import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {
    List<Document> documents=new ArrayList<>();
    Context context;
    OnClick onClickLis;
    public LibraryAdapter(Context context,OnClick onClick) {
        this.context = context;
        this.onClickLis=onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_document, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Document document=documents.get(position);
        holder.bindDocument(document,context);

    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView ivPreview;
        private TextView tvTitle;
        private TextView tvSize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPreview = itemView.findViewById(R.id.ivPreview);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSize = itemView.findViewById(R.id.tvSize);
            itemView.setOnClickListener(this);
        }
        void bindDocument(Document document,Context context){
            Glide.with(context)
                    .load(document.getThumbnailUri())
                    .error(Glide.with(context).load(R.drawable.preview_missing))
                    .into(ivPreview);
            tvSize.setText(StringUtils.readableFileSize(document.getSize()));
            tvTitle.setText(document.getTitle());
        }

        @Override
        public void onClick(View view) {
            onClickLis.onClick(documents.get(getAdapterPosition()));
        }
    }
    public interface OnClick{
        void onClick(Document document);
    }
    void updateData(List<Document> docs){
        documents.clear();
        documents.addAll(docs);
        notifyDataSetChanged();
    }
}
