/*
 *    项目名称:TimeNote
 *    文件名称:NoteAdapter.java
 *    Date:4/4/20 8:05 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.syc.timenote.R;
import com.syc.timenote.bean.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context mContext;
    private List<Note> mNoteList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView noteName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            noteName = itemView.findViewById(R.id.note_name);
        }
    }

    public NoteAdapter(List<Note> noteList) {
        this.mNoteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.noteName.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
