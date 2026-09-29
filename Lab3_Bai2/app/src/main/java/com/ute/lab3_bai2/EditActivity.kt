package com.ute.lab3_bai2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit)

        val edtName = findViewById<EditText>(R.id.edtName)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {

            val name = edtName.text.toString().trim()

            val intent = Intent()
            intent.putExtra("NAME", name)

            setResult(RESULT_OK, intent)

            finish()
        }
    }
}