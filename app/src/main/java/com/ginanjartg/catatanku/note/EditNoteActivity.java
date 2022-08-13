package com.ginanjartg.catatanku.note;

//NIM     : 10119032
//NAMA    : GINANJAR TUBAGUS GUMILAR
//KELAS   : IF-1

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ginanjartg.catatanku.MainActivity;
import com.ginanjartg.catatanku.R;
import com.ginanjartg.catatanku.Database;
import com.ginanjartg.catatanku.NoteModel;

public class EditNoteActivity extends AppCompatActivity {

    private EditText inputTitle, inputCategory, inputNote;
    private Button btn_save;
    private Database database;
    private NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        inputTitle = findViewById(R.id.title);
        inputCategory = findViewById(R.id.category);
        inputNote = findViewById(R.id.note);
        btn_save = findViewById(R.id.btn_save);

        int id = getIntent().getIntExtra("id", 0);

        database = new Database(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(database.selectById(id), null);

        cursor.moveToFirst();

        noteModel = new NoteModel();
        noteModel.setId(id);
        noteModel.setTitle(cursor.getString(1).toString());
        noteModel.setCategory(cursor.getString(2).toString());
        noteModel.setNote(cursor.getString(3).toString());

        cursor.close();

        inputTitle.setText(noteModel.getTitle());
        inputCategory.setText(noteModel.getCategory());
        inputNote.setText(noteModel.getNote());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = inputTitle.getText().toString().trim();
                String category = inputCategory.getText().toString().trim();
                String note = inputNote.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    inputTitle.setError("Judul wajib diisi.");
                } else if (TextUtils.isEmpty(category)) {
                    inputCategory.setError("Kategori wajib diisi.");
                } else if (TextUtils.isEmpty(title)) {
                    inputNote.setError("Catatan wajib diisi.");
                } else {
                    SQLiteDatabase db = database.getWritableDatabase();
                    database.updateTable(db, title, category, note, id);
                    Toast.makeText(EditNoteActivity.this, "Catatan telah diubah.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditNoteActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}