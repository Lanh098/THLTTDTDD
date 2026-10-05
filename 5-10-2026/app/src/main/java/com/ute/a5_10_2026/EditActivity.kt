package com.ute.a5_10_2026

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val etName = findViewById<EditText>(R.id.etName)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val currentName = intent.getStringExtra("KEY_NAME")
        if (currentName != "Chưa có thông tin") {
            etName.setText(currentName)
            etName.setSelection(currentName?.length ?: 0) // Đặt con trỏ cuối chuỗi
        }
        btnSave.setOnClickListener {
            val newName = etName.text.toString().trim()
            val resultIntent = Intent()
            resultIntent.putExtra("KEY_NAME", newName)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}