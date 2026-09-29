package com.ute.lab3_bai2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val editActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {

                val name = result.data?.getStringExtra("NAME")

                if (!name.isNullOrBlank()) {

                    val tvGreeting =
                        findViewById<TextView>(R.id.tvGreeting)

                    tvGreeting.text = "Chào $name"
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val btnGoNext =
            findViewById<Button>(R.id.btnGoNext)

        btnGoNext.setOnClickListener {

            val intent =
                Intent(
                    this,
                    EditActivity::class.java
                )

            editActivityLauncher.launch(intent)
        }
    }
}