package com.ginanjartg.catatanku;

//NIM     : 10119032
//NAMA    : GINANJAR TUBAGUS GUMILAR
//KELAS   : IF-1

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ginanjartg.catatanku.MainActivity;
import com.ginanjartg.catatanku.R;
import com.ginanjartg.catatanku.note.AddNoteActivity;
import com.ginanjartg.catatanku.note.DetailNoteActivity;
import com.ginanjartg.catatanku.note.EditNoteActivity;
import com.ginanjartg.catatanku.NoteAdapter;
import com.ginanjartg.catatanku.Database;
import com.ginanjartg.catatanku.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private ListView listView;
    private List<NoteModel> listNote = new ArrayList<>();
    private Cursor cursor;
    private Database database;
    private FloatingActionButton floatingActionButton;
    private NoteAdapter noteAdapter;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        floatingActionButton = view.findViewById(R.id.btn_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddNoteActivity.class));
            }
        });

        noteAdapter = new NoteAdapter(getContext(), listNote);
        listView = view.findViewById(R.id.container_list_view);
        database = new Database(getContext());
        getAllNote();
        refreshList(view);
        noteAdapter.notifyDataSetChanged();
        return view;
    }

    private void getAllNote() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery(database.selectAll(), null);
        listNote.clear();
        if (cursor.moveToFirst()){
            do {
                NoteModel note = new NoteModel();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setCategory(cursor.getString(2));
                note.setNote(cursor.getString(3));
                listNote.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        noteAdapter.notifyDataSetChanged();
    }

    private void refreshList(View view) {
        listView.setAdapter(noteAdapter);
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int id = listNote.get(i).getId();
                final CharSequence[] dialogItem = {
                        "Detail Catatan",
                        "Edit Catatan",
                        "Hapus Catatan"
                };
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent_detail = new Intent(requireActivity(), DetailNoteActivity.class);
                                intent_detail.putExtra("id", id);
                                startActivity(intent_detail);
                                break;
                            case 1:
                                Intent intent_edit = new Intent(getActivity(), EditNoteActivity.class);
                                intent_edit.putExtra("id", id);
                                startActivity(intent_edit);
                                break;
                            case 2:
                                SQLiteDatabase db = database.getWritableDatabase();
                                database.deleteById(db, id);
                                Toast.makeText(getContext(), "Berhasil menghapus catatan.", Toast.LENGTH_SHORT).show();
//                                refreshList(getView());
                                getAllNote();
                                break;
                            default:
                                i = 0;
                        }
                    }
                }).show();
            }
        });
    }

}