package com.ginanjartg.catatanku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private Context context;
    private List<NoteModel> noteAdapter;
    private LayoutInflater layoutInflater;
    private TextView title, category;

    public NoteAdapter(Context context, List<NoteModel> noteAdapter) {
//        this.activity = activity;
        this.context = context;
        this.noteAdapter = noteAdapter;
    }

    @Override
    public int getCount() {
        return noteAdapter.size();
    }

    @Override
    public Object getItem(int i) {
        return noteAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        if (convertView != null) {
            NoteModel note = noteAdapter.get(position);
            title = convertView.findViewById(R.id.title);
            category = convertView.findViewById(R.id.category);
            title.setText(note.getTitle());
            category.setText(note.getCategory());
        }
        return convertView;
    }
}
