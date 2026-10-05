package com.ute.a5_10_2026

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newName = result.data?.getStringExtra("KEY_NAME")
            if (!newName.isNullOrEmpty()) {
                tvName.text = newName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvName = findViewById(R.id.tvName)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("KEY_NAME", tvName.text.toString())
            editLauncher.launch(intent)
        }
    }
}