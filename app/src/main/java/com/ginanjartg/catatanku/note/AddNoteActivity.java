package com.ginanjartg.catatanku.note;

//NIM     : 10119032
//NAMA    : GINANJAR TUBAGUS GUMILAR
//KELAS   : IF-1

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AddNoteActivity extends AppCompatActivity {

    private EditText inputTitle, inputCategory, inputNote;
    private Button btn_add;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        database = new Database(this);

        inputTitle = findViewById(R.id.title);
        inputCategory = findViewById(R.id.category);
        inputNote = findViewById(R.id.note);

        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
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
                    database.insertToTable(db, title, category, note);
                    Toast.makeText(AddNoteActivity.this, "Catatan telah ditambahkan.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}